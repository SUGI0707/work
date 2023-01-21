package timeChecker;

public class FunctionFile {

	
	
	public static Boolean checkNumber(int target) {
		boolean result = false ;
		//時刻に使われる数字かをチェック
		if(0 <= target && target <= 23) {
			result = true;
		}else {
			result = false;
		}
		return result;
	}
	
	/* 入力された指定時刻（targetNumber）が開始・終了時刻間に当てはまるかを確認する */
	/* 当てはまる  true */
	/* 当てはまらない false */

	public static boolean checkTarget(int startNumber,int endNumber, int targetNumber) {
		boolean result = false;

		//日付をまたいでいない場合（例:9時～18時）
		//開始時刻より前なら期間外
		if(startNumber < endNumber) {
			if(startNumber <= targetNumber) {
				result = true;
			}else {
				result = false;
				return result;
			}
			//終了時刻より後なら期間外
			if(targetNumber < endNumber) {
				result = true;
			}else {
				result = false;
				return result;
			}
		}
		
		//日付をまたいでいる場合（例:22時～3時）
		//開始時刻より前なら期間外		
		if(endNumber < startNumber) {
			if(startNumber <= targetNumber || targetNumber < endNumber) {
				result = true;
			}else{
				result = false;
				return result;
			}	
		}
		
		//開始時刻と終了時刻が同じ場合
		//開始時刻、終了時刻と同じであれ期間内		
		if(endNumber == startNumber) {
			if(startNumber == targetNumber) {
				result = true;
			}else{
				result = false;
				return result;
			}	
		}

		return result ;
	}
}
