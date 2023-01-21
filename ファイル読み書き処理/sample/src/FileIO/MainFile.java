package FileIO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

	/* 
	 * あまり実用的な想定ではないですが
	 * ファイルの内容を加工して別ファイルに書き起こす処理です。
	 * 
	 * 
	 * 
	 * 
	 * 着手日時・工数　2023年1月18日22時～2023年1月19日1時
	 * 読み込み用ファイルからデータを抜き出し
	 * 書き込み先ファイルにデータを書き込む
	 * 
	 * 改修日時・工数　2023年1月19日23時～2023年1月20日1時
	 * ・メッセージ定数用のMessageConstを追加
	 * ・MAINに記載されていたnullチェック処理をFunctionFileに移動
	 * 
	 * 改修日時・工数　2023年1月20日21時～2023年1月2日23時
	 * ・書き込み先ファイルのヘッダー情報をプロパティファイルに移動
	 * 
	 * */

public class MainFile {

	public static void main(String[] args) {
		// 書き込み先と読み込み先の定義
		File inputFile = new File(".\\FileIO\\input.txt");
		File outputFile = new File(".\\FileIO\\output.txt");

	    //プロパティファイルのパスを指定する
		Properties properties = new Properties();
	    String prpertiesPass = ".\\FileIO\\Properties.prperties";

		//メッセージ用
		JFrame frame = new JFrame();

		//作業用文字列変数
		String str;
		
		//変数定義
		String NAME = "";
		String AGE = "";
		String PHONE = "";
		String DEPT = "";
		String POSITION = "";

		//読み込み・書き込み
		FileReader fr;
		FileWriter fw;
		try {
	        
			InputStream istream = new FileInputStream(prpertiesPass);
	        
			//プロパティファイルの中身を呼び出す
	        properties.load(istream);
	        
			fr = new FileReader(inputFile);
			fw = new FileWriter(outputFile);
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			
			PrintWriter pw = new PrintWriter(bw);
			//読み込み内容格納用
			String text;
			
			//最初にヘッダーを一行目に設定
			bw.write(properties.getProperty("HEADER"));
			bw.newLine();

			
			//読み込んで空でない限り処理する
			while ((text = br.readLine()) != null) {
				//読み込んだ内容を区切り文字で分割しリストに格納する。
				List <String>lists_before = new ArrayList<String>(); 
				List <String>lists = new ArrayList<String>(); 
				lists_before = Arrays.asList(text.split(","));
				
				
				//読み込んだ項目をまとめてNULLチェックし別リストに格納
				for (int c = 0; c < lists_before.size(); c++) {
					 lists.add(FunctionFile.checkNull(lists_before.get(c)));
				}
				
				//氏名
				//concatの最初はNULL
				str = FunctionFile.checkNull(lists.get(0)).concat(" ");
				NAME = str.concat(lists.get(1));
				bw.write(NAME);
				bw.write(",");
				
				//年齢
				AGE = lists.get(2);
				bw.write(AGE);
				bw.write(",");
				
				//電話番号
				str = "+";
				str = str.concat(lists.get(3));
				str = str.concat("-");
				str = str.concat(lists.get(4));
				str = str.concat("-");
				str = str.concat(lists.get(5));
				str = str.concat("-");
				PHONE = str.concat(lists.get(6));
				bw.write(PHONE);
				bw.write(",");

				//所属
				str = lists.get(7).concat(" ");
				str = str.concat(lists.get(8));
				str = str.concat(" ");
				DEPT = str.concat(lists.get(9));
				bw.write(DEPT);
				bw.write(",");
				
				//職位
				POSITION = lists.get(10);
				bw.write(POSITION);
				
				//改行処理
				bw.	newLine();
			}
			pw.close();
			br.close();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(frame, ConstFile.ERROR_IO_EXCEPTION);
		} finally {
			JOptionPane.showMessageDialog(frame, ConstFile.INFO_COMPLETE);
		}

	}
}




