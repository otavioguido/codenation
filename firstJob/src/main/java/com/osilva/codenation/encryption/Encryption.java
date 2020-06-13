package com.osilva.codenation.encryption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.osilva.codenation.encryption.model.EncryptionObject;

public class Encryption {
	private static final String GET_URL = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=";
	private static final String POST_URL = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=";
	private static final String TOKEN = "40072faa84e321e6ae7cb750c3f173dcd14aec19";
	private static final int Z_CHAR = 122;
	private static final int A_CHAR = 97;
	private static final int ALPHABET_SIZE = 26;

	public static void main(String[] args) {
		EncryptionObject responseContent = getRequest();
		
		responseContent.setDecifrado(decryptMessage(responseContent.getCifrado(), responseContent.getNumero_casas()));
		
		responseContent.setResumo_criptografico(DigestUtils.sha1Hex(responseContent.getDecifrado()));
		
		saveFile(responseContent);
		
		postRequest();
		
		System.out.println(responseContent.toString());
	}
	
	private static void saveFile(EncryptionObject encryptionObject) {
		try {
			FileWriter file = new FileWriter("answer.json");
			file.write(new Gson().toJson(encryptionObject));
            file.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static void postRequest() {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(POST_URL+TOKEN);
		File file = new File("answer.json");
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addBinaryBody("answer", file, ContentType.DEFAULT_BINARY, "answer.json");
		HttpEntity entity = builder.build();
		
		try {
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			
			System.out.println(response.getStatusLine().getStatusCode());
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static EncryptionObject getRequest() {
		EncryptionObject responseContent = null;
		
		try {
			// get request to codenation to get json
			URL url = new URL(GET_URL+TOKEN);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();			
			// convert response content to json
			responseContent = getResponseContent(con.getInputStream());
			con.disconnect();
			
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return responseContent;
	}
	
	private static EncryptionObject getResponseContent(InputStream inputStream) {
		String fullContent = "";
		
		try {
			// convert input stream to string
			BufferedReader in = new BufferedReader(
					  new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			StringBuffer content = new StringBuffer();
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			fullContent = content.toString();
			in.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return new Gson().fromJson(fullContent, EncryptionObject.class);
	}
	
	private static String decryptMessage(String encrypted, int numero_casas) {
		char[] charArray = encrypted.toCharArray();
		
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] >= A_CHAR && charArray[i] <= Z_CHAR) {
				int aux = ((int) charArray[i]) - numero_casas;
				
				if (aux < A_CHAR) {
					aux+= ALPHABET_SIZE;
				}
				
				charArray[i] = (char) aux;
			}
		}
		return new String(charArray);
	}

}
