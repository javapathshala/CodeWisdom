function GetXmlHttpObject() {
	var xmlHttp=null;
	try{
		xmlHttp=new XMLHttpRequest();
	}catch(e){
		try{
			xmlHttp=new ActiveXObject("Mxsml2.XMLHTTP");
			}catch(e){
				try{
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");						
					}catch(e){
						alert("Browser Doesnot Support AJAX !!");
						return false;
					}
				}
			}			
	return xmlHttp;
	}				
	
	function getNewHTTPObject()
{
        var xmlhttp;

        /** Special IE only code ... */
        /*@cc_on
          @if (@_jscript_version >= 5)
              try
              {
                  xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
              }
              catch (e)
              {
                  try
                  {
                      xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                  }
                  catch (E)
                  {
                      xmlhttp = false;
                  }
             }
          @else
             xmlhttp = false;
        @end @*/

        /** Every other browser on the planet */
        if (!xmlhttp && typeof XMLHttpRequest != 'undefined')
        {
            try
            {
                xmlhttp = new XMLHttpRequest();
            }
            catch (e)
            {
                xmlhttp = false;
            }
        }

        return xmlhttp;
}
	