package com.osilva.codenation.encryption.utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.osilva.codenation.encryption.model.EncryptionObject;

public class Utils {
	
	/*private String decryptMessage(String encrypted, int numero_casas) {
		char[] charArray = encrypted.toCharArray();
		
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] >= A_CHAR && charArray[i] <= Z_CHAR) {
				int aux = ((int) charArray[i]) + numero_casas;
				
				if (aux > Z_CHAR) {
					aux-= ALPHABET_SIZE;
				}
				
				charArray[i] = (char) aux;
			}
		}
		return new String(charArray);
	}
	
	private String getResponseContent(InputStream inputStream) {
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
		return fullContent;
		//return new Gson().fromJson(fullContent, EncryptionObject.class);
	}
	
	private void saveFile(EncryptionObject encryptionObject) {
		try {
			FileWriter file = new FileWriter("answer.json");
			file.write(new Gson().toJson(encryptionObject));
            file.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}*/
}
