import java.util.regex.Pattern;

public class RegistrationValidator {

    public boolean validate(String toValidate){

        Pattern pattern = Pattern.compile("\\d_\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}_[1-9]\\d{4}");

        return pattern.matcher(toValidate).matches();
    }
}
