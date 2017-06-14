package users;

import org.springframework.security.crypto.password.StandardPasswordEncoder;



public class PasswordEncoder {

	public static String encodePassword(String password){
		StandardPasswordEncoder encoder = new StandardPasswordEncoder("secret");
		String encoded = encoder.encode(password);
		System.out.println(encoded);
		return encoded;
	}

	public static boolean verifyPassword(String password, String encryptedPassword){
		StandardPasswordEncoder encoder = new StandardPasswordEncoder("secret");
		return encoder.matches(password, encryptedPassword);
	}

	public static void main(String[] args){
		String s = encodePassword("admin");
		System.out.println(s);
	}

}
