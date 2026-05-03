import java.util.*;

public class Main {
    static Map<String, Voter> voters = new HashMap<>();
    static Map<String, Candidate> candidates = new HashMap<>();
    static Blockchain blockchain = new Blockchain();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1. Add Candidate");
            System.out.println("2. Add Voter");
            System.out.println("3. Cast Vote");
            System.out.println("4. Print Blockchain");
            System.out.println("5. Validate Chain");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addCandidate();
                case 2 -> addVoter();
                case 3 -> castVote();
                case 4 -> blockchain.printChain();
                case 5 -> System.out.println(blockchain.isValid());
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }

    static void addCandidate() {
        System.out.print("Enter ID: ");
        String id = sc.next();

        if (candidates.containsKey(id)) {
            System.out.println("Duplicate candidate!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.next();

        candidates.put(id, new Candidate(id, name));
    }

    static void addVoter() {
        System.out.print("Enter ID: ");
        String id = sc.next();

        if (voters.containsKey(id)) {
            System.out.println("Duplicate voter!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.next();

        voters.put(id, new Voter(id, name));
    }

    static void castVote() {
        System.out.print("Enter Voter ID: ");
        String voterId = sc.next();

        if (!voters.containsKey(voterId)) {
            System.out.println("Voter not found!");
            return;
        }

        Voter voter = voters.get(voterId);

        if (voter.hasVoted) {
            System.out.println("Already voted!");
            return;
        }

        System.out.print("Enter Candidate ID: ");
        String candidateId = sc.next();

        if (!candidates.containsKey(candidateId)) {
            System.out.println("Candidate not found!");
            return;
        }

        Vote vote = new Vote(voterId, candidateId);
        blockchain.addBlock(List.of(vote));

        voter.hasVoted = true;

        System.out.println("Vote recorded!");
    }
}