import java.util.*;

class Blockchain {
    List<Block> chain;

    public Blockchain() {
        chain = new ArrayList<>();
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block("0", new ArrayList<>());
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(List<Vote> votes) {
        Block newBlock = new Block(getLatestBlock().hash, votes);
        chain.add(newBlock);
    }

    public boolean isValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block current = chain.get(i);
            Block previous = chain.get(i - 1);

            if (!current.hash.equals(current.calculateHash())) return false;
            if (!current.previousHash.equals(previous.hash)) return false;
        }
        return true;
    }

    public void printChain() {
        for (Block block : chain) {
            System.out.println("Hash: " + block.hash);
            System.out.println("Prev: " + block.previousHash);
            System.out.println("Votes: " + block.votes);
            System.out.println("-------------------");
        }
    }
}