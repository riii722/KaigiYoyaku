/**
 * 予約詳細画面
 */

// 入力不可にしたい項目を定義
var yoyakuDay = (document.getElementById("input_yoyakuDay") != null ? document.getElementById("input_yoyakuDay") : "");
var startTime = (document.getElementById("input_startTime") != null ? document.getElementById("input_startTime") : "");
var endTime = (document.getElementById("input_endTime") != null ? document.getElementById("input_endTime") : "");
var kaigiName = (document.getElementById("input_kaigiName") != null ? document.getElementById("input_kaigiName") : "");
var kaigiSpace = (document.getElementById("input_kaigiSpace") != null ? document.getElementById("input_kaigiSpace") : "");
var yoyakuName = (document.getElementById("input_yoyakuName") != null ? document.getElementById("input_yoyakuName") : "");

// 要素を入力不可にする
yoyakuDay.disabled = true;
startTime.disabled = true;
endTime.disabled = true;
kaigiName.disabled = true;
kaigiSpace.disabled = true;
yoyakuName.disabled = true;

// 入力不可を解除するきっかけにしたい項目を定義
var btn_edit = document.getElementById( 'btn_edit' );
var btn_delete = document.getElementById( 'btn_delete' );

// 編集ボタンが押下されたら入力可にする
btn_edit.addEventListener( 'click' , function(){
      yoyakuDay.disabled = false;
      startTime.disabled = false;
      endTime.disabled = false;
      kaigiName.disabled = false;
      kaigiSpace.disabled = false;
      yoyakuName.disabled = false;
  });

// 削除ボタンが押下されたら入力不可にする
btn_delete.addEventListener( 'click' , function(){
	yoyakuDay.disabled = true;
	startTime.disabled = true;
    endTime.disabled = true;
	kaigiName.disabled = true;
	kaigiSpace.disabled = true;
	yoyakuName.disabled = true;
});
