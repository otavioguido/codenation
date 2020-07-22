package challenge;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "scripts")
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String actor;

	@Column(name = "detail")
	private String quote;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActor() {
		return this.actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getQuote() {
		return this.quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}
}
