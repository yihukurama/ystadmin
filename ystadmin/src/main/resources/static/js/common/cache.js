
function setCookie(c_name,value,expiredays)
{
    var exdate=new Date()
    exdate.setDate(exdate.getDate()+expiredays)
    document.cookie=projectName+c_name+ "=" +encodeURI(JSON.stringify(value))+
        ((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
}

function getCookie(c_name)
{
    if (document.cookie.length>0)
    {
        c_start=document.cookie.indexOf(projectName+c_name + "=")
        if (c_start!=-1)
        {
            c_start=c_start + c_name.length+1
            c_end=document.cookie.indexOf(";",c_start)
            if (c_end==-1) c_end=document.cookie.length
            return decodeURI(document.cookie.substring(c_start,c_end))
        }
    }
    return ""
}

/**
 *Localstorage 关闭浏览器还存在
 */

//存储userId到localstorage
function setLocalStorageUid(userId){
	localStorage.userId=value;
	document.write(localStorage.userId);
}
//存储token到localstorage
function setLocalStorageToken(token){
	localStorage.token=token;
	document.write(localStorage.token);
}


/**
 * SessionStorage 关闭浏览器自动清空
 */
//存储userId到SessionStorage
function setSessionStorageUid(userId){
	sessionStorage.userId=userId;
	document.write(sessionStorage.userId);
}
//存储token到SessionStorage
function setSessionStorageUid(token){
	sessionStorage.token=token;
	document.write(sessionStorage.token);
}