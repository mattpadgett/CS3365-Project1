package util;

public class Authentication {
	public static String hashPassphrase(String passphrase) {
		return BCrypt.hashpw(passphrase, BCrypt.gensalt(12));
	}
	
	public static boolean verifyPassphrase(String passphrase, String hash) {
		return BCrypt.checkpw(passphrase, hash);
	}
}
