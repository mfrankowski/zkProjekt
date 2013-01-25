package zkProjekt;

import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class Validator extends AbstractValidator {
	
	public void validate(ValidationContext ctx) {
		//all the bean properties
		Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());
		
		//first let's check the passwords match
		validateNumRej(ctx, (String)beanProps.get("numRej").getValue());
	}


	private void validateNumRej(ValidationContext ctx, String numRej) {	
		if(numRej == null) {
			this.addInvalidMessage(ctx, "numRej", "Podaj numer rejestracyjny!");
		}
	}
	
}
