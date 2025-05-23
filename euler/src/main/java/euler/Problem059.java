package euler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utils.Utils;

/**
 * </div>
 * <h2>XOR decryption</h2> <div id="problem_info" class="info">
 * <h3>Problem 59</h3> <span>Published on Friday, 19th December 2003, 06:00 pm;
 * Solved by 27343; Difficulty rating: 5%</span> </div>
 * <div class="problem_content" role="problem">
 * <p>
 * Each character on a computer is assigned a unique code and the preferred
 * standard is ASCII (American Standard Code for Information Interchange). For
 * example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
 * </p>
 * <p>
 * A modern encryption method is to take a text file, convert the bytes to
 * ASCII, then XOR each byte with a given value, taken from a secret key. The
 * advantage with the XOR function is that using the same encryption key on the
 * cipher text, restores the plain text; for example, 65 XOR 42 = 107, then 107
 * XOR 42 = 65.
 * </p>
 * <p>
 * For unbreakable encryption, the key is the same length as the plain text
 * message, and the key is made up of random bytes. The user would keep the
 * encrypted message and the encryption key in different locations, and without
 * both "halves", it is impossible to decrypt the message.
 * </p>
 * <p>
 * Unfortunately, this method is impractical for most users, so the modified
 * method is to use a password as a key. If the password is shorter than the
 * message, which is likely, the key is repeated cyclically throughout the
 * message. The balance for this method is using a sufficiently long password
 * key for security, but short enough to be memorable.
 * </p>
 * <p>
 * Your task has been made easy, as the encryption key consists of three lower
 * case characters. Using
 * <a href="project/resources/p059_cipher.txt">cipher.txt</a> (right click and
 * 'Save Link/Target As...'), a file containing the encrypted ASCII codes, and
 * the knowledge that the plain text must contain common English words, decrypt
 * the message and find the sum of the ASCII values in the original text.
 * </p>
 * </div> <br>
 * <br>
 */
public class Problem059 {

	public static void main(String[] args) throws IOException {
		List<Byte> bytes = Arrays
				.stream(Files.lines(Paths.get("src", "main", "docs", "cipher.txt"))
						.collect(Collectors.joining(",")).split(","))
				.map(s -> Byte.valueOf(s).byteValue()).collect(Collectors.toList());
		// findEncryptChar(bytes, 0);
		// findEncryptChar(bytes, 1);
		// findEncryptChar(bytes, 2);
		Stream<Byte> encryptChars = Stream.iterate((byte) 103, b -> {
			switch (b) {
			case 103:
				return (byte)111;
			case 111:
				return (byte)100;
			case 100:
				return (byte)103;
			default:
				return (byte)0;
			}
		}).limit(bytes.size());
		String text = Utils.zip(bytes.stream(), encryptChars, (b, e) -> b ^ e)
				.map(i -> String.valueOf((char) i.intValue()))
				.collect(Collectors.joining());
		System.out.println(text);
		System.out.println(text.chars().sum());
	}

	private static void findEncryptChar(List<Byte> bytes, int start) {
		Map<Character, Integer> counts = new HashMap();
		for (byte i = 97; i < 123; i++) {
			int fits = 0;
			for (int j = start; j < bytes.size(); j += 3) {
				byte byteValue = bytes.get(j).byteValue();
				int b = i ^ byteValue;
				if (!(65 <= b && b <= 90 || 97 <= b && b <= 122)) {
					// System.out.println("misfit: " + b + ", " + (char) b);
				} else {
					fits++;
					// System.out.println(b + ", " + (char) b);
				}
			}
			// System.out
			// .println("encrypt char: " + i + ", " + (char) i + " fits: " + fits);
			counts.put(Character.valueOf((char) i), Integer.valueOf(fits));
		}

		counts.entrySet().stream().sorted((a, b) -> a.getValue() - b.getValue())
				.collect(Collectors.toList());
	}
}
