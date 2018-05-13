package FileIndexRename;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.StringBuilder;

// ファイル名のインデックス番号をリネーム
class IndexRename {
  public static final int INPUT_DATA_LENGTH = 3;
 
  public static void main(String[] args) {
	  
	  // 入力値1：フォルダのフルパス
	  // 入力値2：インデックス開始番号
	  // 入力値3：スプリット文字
	  String[] inputData = new String[INPUT_DATA_LENGTH];

	 try {
		// 入力値記載テキストファイルを読み込み
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("E:\\Tak_lib\\04_Java\\FileIndexRename\\inputData.txt")));
		
		// テキスト読み込み
		String temp;
		int i = 0;
		while (i < INPUT_DATA_LENGTH && (temp = bufferedReader.readLine()) != null) {
			inputData[i] = temp;
			i++;
		}
		
		// ファイルクローズ
		bufferedReader.close();
	} catch (Exception e) {
		e.printStackTrace();
	}

	 // ファイルリストの取得
    File[] files = new File(inputData[0]).listFiles();

    // インデックス開始番号の設定
    int indexNum = 1;
    indexNum = Integer.parseInt(inputData[1]);

    // スプリット文字の設定
    // デフォルトは"_"（アンダースコア）
    String splitChar = "_";

    if(inputData[2] != null && inputData[2].length() != 0){
      splitChar = inputData[2];
    }
    
    // ファイル名先頭のインデックスを変更
    for (File file : files) {
      fileIndexRename(file, indexNum, splitChar);
      indexNum++;
    }
    
    System.out.println("Fin.");
  }

  private static void fileIndexRename(File file, int index, String splitChar){
    // ファイルのインデックス部分を取得、リネーム
    String[] splitFileName = file.getName().split(splitChar,2);
    splitFileName[0] = String.format("%02d", index) + "_";

    // ファイルパスを生成
    StringBuilder renamedFileName = new StringBuilder();
    for(String str : splitFileName){
      renamedFileName.append(str);
    }

    // ファイルリネーム
    Boolean isSuccess = file.renameTo(new File(file.getParent() + "\\" + renamedFileName.toString()));

    if(!isSuccess){
    	System.out.println("file path: " + file.getPath() + "\n" + "renamed file name: " + renamedFileName.toString());
    }
  }
}