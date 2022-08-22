/**
 * 初期表示 init()
 */
function init() {

  //リクエストJSON
  var request = {
	year : "",
	month : "",
	day: ""
  };

  //ajaxでservletにリクエストを送信
  $.ajax({
    type    : "GET",          //GET / POST
    url     : "http://localhost:9080/YoyakuSystem/SYS00100",  //送信先のServletURL
    data    : request,        //リクエストJSON
    async   : true,           //true:非同期(デフォルト), false:同期
    success : function(data) {
      //通信が成功した場合に受け取るメッセージ
      response1 = data["response1"];
      response2 = data["response2"];
    },
    error : function(XMLHttpRequest, textStatus, errorThrown) {
      alert("リクエスト時になんらかのエラーが発生しました：" + textStatus +":\n" + errorThrown);
    }
  });

}
