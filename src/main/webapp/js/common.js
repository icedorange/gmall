/**
 * 页面传值公用方法
 * 使用样例
 * var str ="www.jb51.net/index.htm?a=1&b=1&c=测试测试";
 * alert(str.getQuery("a"));
 * alert(str.getQuery("b"));
 * alert(str.getQuery("c"));
 * 
 * 获取浏览器地址
 * alert(location.search);  ?googId=5555
 * alert(document.URL);     http://localhost:8081/dymall/home/introduction.html?googId=5555
 */
String.prototype.getQuery = function(name)
{
 　　var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
 　　var r = this.substr(this.indexOf("?")+1).match(reg);
 　　if (r!=null) return unescape(r[2]); return null;
}