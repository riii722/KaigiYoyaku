/**
 * 共通処理
 */

// 初期処理
function openWindow(url){
	
	//ウィンドウのサイズ設定
	ww = '800';
	wh = '500';
	// 表示位置設定
	w = (screen.availWidth - ww) / 2;
	h = (screen.availHeight - wh) / 2;
	
	//新規ウィンドウで開く
	window.open(url, null, 'width=' + ww + ',height=' + wh + ',left=' + w  + ',top=' + h);	
}

// 入力チェック処理
function check(){
	var yoyakuForm = document.getElementByName("yoyakuForm");
    if (yoyakuForm.yoyakuDay.value == ""){
        alert("予約日を入力してください");    //エラーメッセージを出力
        return false;    //送信ボタンの動作をキャンセルする
    }else if (yoyakuForm.startTime.value == ""){
        alert("開始時間を入力してください");    //エラーメッセージを出力
        return false;    //送信ボタンの動作をキャンセルする
    }else if (yoyakuForm.endTime.value == ""){
        alert("終了時間を入力してください");    //エラーメッセージを出力
        return false;    //送信ボタンの動作をキャンセルする
    }else if (yoyakuForm.itemNum.value == ""){
        alert("会議場所を入力してください");    //エラーメッセージを出力
        return false;    //送信ボタンの動作をキャンセルする
    }else if (yoyakuForm.kaigiName.value == ""){
        alert("会議名を入力してください");    //エラーメッセージを出力
        return false;    //送信ボタンの動作をキャンセルする
    }else if (yoyakuForm.yoyakuName.value == ""){
        alert("予約者名を入力してください");    //エラーメッセージを出力
        return false;    //送信ボタンの動作をキャンセルする
    }else{
        //条件に一致しない場合
        return true;    //送信ボタンの動作を実行する
    }
}