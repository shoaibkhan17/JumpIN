/**
 * Board listener interface class
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public interface BoardListener {
	/**
	 * Interface method to handle the board events
	 * @param eventType
	 * @param square if a square is required to be highlighted
	 */
	public void BoardEventHandler(Constants.BoardEventType eventType, Square square);
}
