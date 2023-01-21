package FileIO;

public class FunctionFile {
	
	/* 
	 * nullであれば空文字として返す
	 * nullでなければそのままの値を返す
	 *  */
	
	
	public static String checkNull(String target) {
		if(target == null) {
			return "";
		}else {
			return target;
		}
	}
}
