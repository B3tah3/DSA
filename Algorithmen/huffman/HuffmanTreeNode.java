package huffman;

public class HuffmanTreeNode {

	protected HuffmanTreeNode left;
	protected HuffmanTreeNode right;
	private long value;
	private int character;

	public HuffmanTreeNode(final int character, final long value) {
		this.left = null;
		this.value = value;
		this.character = character;
		this.right = null;
	}

	/**
	 * 
	 * @return
	 */
	HuffmanTreeNode getLeft() {
		return left;
	}

	/**
	 * 
	 * @param left
	 */
	void setLeft(HuffmanTreeNode left) {
		this.left = left;
	}

	/**
	 * 
	 * @return
	 */
	HuffmanTreeNode getRight() {
		return right;
	}

	/**
	 * 
	 * @param right
	 */
	void setRight(HuffmanTreeNode right) {
		this.right = right;
	}

	/**
	 * 
	 * @return
	 */
	long getValue() {

		return value;
	}

	/**
	 * 
	 * @param value
	 */
	void setValue(long value) {
		this.value = value;
	}

	/**
	 * 
	 * @return
	 */
	boolean hasRight() {

		return this.right != null;
	}

	/**
	 * 
	 * @return
	 */
	boolean hasLeft() {
		return this.left != null;
	}

	public int getCharacter() {
		return character;
	}

	public boolean hasNext() {
		return this.hasLeft() || this.hasRight();
	}

	HuffmanTreeNode getNext(boolean right) {
		HuffmanTreeNode returnNode = this.getLeft();
		if (right) {
			returnNode = this.getRight();
		}
		return returnNode;
	}

}
