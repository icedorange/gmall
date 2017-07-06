/**
 * 页面传值公用方法 使用样例 var str ="www.jb51.net/index.htm?a=1&b=1&c=测试测试";
 * alert(str.getQuery("a")); alert(str.getQuery("b")); alert(str.getQuery("c"));
 * 
 * 获取浏览器地址 alert(location.search); ?googId=5555 alert(document.URL);
 * http://localhost:8081/dymall/home/introduction.html?googId=5555
 */
String.prototype.getQuery = function(name)
{
 　　var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
 　　var r = this.substr(this.indexOf("?")+1).match(reg);
 　　if (r!=null) return unescape(r[2]); return null;
}
// 定义全局变量
$.extend({
	gmallHost:"http://www.gmall.com/gmall"
});

// 全局的ajax访问，处理ajax清求时sesion超时
$.ajaxSetup({
// contentType : "application/x-www-form-urlencoded;charset=utf-8",
    complete : function(xhr, textStatus) {
    	// 通过XMLHttpRequest取得响应头，sessionstatus
        var sessionstatus = xhr.getResponseHeader("sessionstatus"); 
        if (sessionstatus == "0") {
            // 如果超时就处理 ，指定要跳转的页面
            window.location.replace("http://www.gmall.com/gmall/home/login.html");
        }
    }
});