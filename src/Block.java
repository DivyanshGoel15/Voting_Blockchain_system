import java.security.MessageDigest;
import java.util.*;

class Block {
    String previousHash;
    String hash;
    List<Vote> votes;
    long timestamp;

    public Block(String previousHash, List<Vote> votes) {
        this.previousHash = previousHash;
        this.votes = votes;
        this.timestamp = System.currentTimeMillis();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String data = previousHash + votes.toString() + timestamp;
        return sha256(data);
    }

    private String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hex = new StringBuilder();

            for (byte b : hashBytes) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}