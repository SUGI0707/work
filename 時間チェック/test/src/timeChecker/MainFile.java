package timeChecker;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MainFile {
	
	
	/* 
	 * 【要件】
	 * ある時刻と、時間の範囲（開始時刻と終了時刻）を受け取る
	 * 時刻は6時であれば６のような指定でよく、分単位は問わない
	 * 範囲指定は開始時刻を含み、終了時刻を含まないと判断すること
	 * ただし開始時刻と終了時刻が同じ場合は含むと判断すること
	 * 開始時刻が22時で終了時刻が5時というような指定をされても動作すること
	 * 
	 * 
	 * 
	 * 着手日時・工数　2023年1月16日22時～2023年1月17日1時
	 * ・入力チェックや時刻としての数字の整合性チェック等の詳細処理はFunctionFileから呼び出す
	 * ・ウィンドウに表示される文言は定数定義のファイルから参照する
	 * ・取り消しやエスケープの場合はNULLで例外を拾う
	 * ・空入力の場合は数字変換時に例外を拾う
	 * 
	 * 改修日時・工数　2023年1月17日21時～2023年1月17日23時
	 * ・NULLチェックを追加
	 * ・メッセージ定数用のMessageConstを追加
	 * ・MAINに記載されていたチェック処理をFunctionFileに移動
	 * 
	 * */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		// 0.事前定義
		//メッセージ表示用
		JFrame frame = new JFrame();
		
		//チェック結果格納用
		boolean checkStartResult = false;
		boolean checkEndResult = false;
		boolean checkTargetResult = false;
		boolean checkResult = false;
		
		//数字に変換された入力値
		int startTime = 0;
		int endTime = 0;
		int targetTime = 0;
		
		try {
			// 1.ウィンドウから入力された値のチェック
			//入力チェック（開始時刻）
			String start = JOptionPane.showInputDialog(frame, MessageConst.INPUT_START_TIME_);
			System.out.println(MessageConst.INFO_START_TIME + start);
			
			//入力された値が時刻として使用できるかをチェック
			if(start != null) {
				startTime = Integer.parseInt(start);
				checkStartResult = FunctionFile.checkNumber(startTime);
				if(checkStartResult == false) {
					JOptionPane.showMessageDialog(frame, MessageConst.ERROR_NOT_NUMBER_FOR_TIME);
					System.out.println(MessageConst.INFO_INCORRECT_NUMBER + start);
					return;
				}	
			}else {
				throw new NullPointerException();
			}
			
			
			
			//入力チェック（終了時刻）
			String end = JOptionPane.showInputDialog(frame, MessageConst.INPUT_END_TIME_);
			System.out.println(MessageConst.INFO_END_TIME + end);

			//入力された値が時刻として使用できるかをチェック
			if(end != null) {
				endTime = Integer.parseInt(end);
				checkEndResult = FunctionFile.checkNumber(endTime);
				if(checkEndResult == false) {
					JOptionPane.showMessageDialog(frame, MessageConst.ERROR_NOT_NUMBER_FOR_TIME);
					System.out.println(MessageConst.INFO_INCORRECT_NUMBER + end);
					return;
				}
			}else {
				throw new NullPointerException();
			}
			
			//入力チェック（指定時刻）
			String target = JOptionPane.showInputDialog(frame, MessageConst.INPUT_TARGET_TIME_);
			System.out.println(MessageConst.INFO_TARGET_TIME + target);
			
			//入力された値が時刻として使用できるかをチェック
			if(target != null) {
				targetTime = Integer.parseInt(target);
				checkTargetResult = FunctionFile.checkNumber(targetTime);

				if(checkTargetResult == false) {
					JOptionPane.showMessageDialog(frame, MessageConst.ERROR_NOT_NUMBER_FOR_TIME);
					System.out.println(MessageConst.INFO_INCORRECT_NUMBER + target);
					return;
				}
			}else {
				throw new NullPointerException();
			}
			
			
			// 2.入力された時刻が期間内であるかをチェック
			checkResult = FunctionFile.checkTarget(startTime, endTime, targetTime);
			if(checkResult == false) {
				JOptionPane.showMessageDialog(frame, MessageConst.FALSE);
				System.out.println(MessageConst.FALSE);
				return;
			}else{
				JOptionPane.showMessageDialog(frame, MessageConst.TRUE);
				System.out.println(MessageConst.TRUE);
			}
			
		}catch (NumberFormatException e){
			//数字として扱えない値はエラーメッセージを表示して終了
			JOptionPane.showMessageDialog(frame, MessageConst.ERROR_NOT_NUMBER);			
		}catch (NullPointerException e){
			JOptionPane.showMessageDialog(frame, MessageConst.ERROR_NULL);
		}finally {
			JOptionPane.showMessageDialog(frame, MessageConst.CLOSE_PROGRAM);
		}
		
		
	}

}
