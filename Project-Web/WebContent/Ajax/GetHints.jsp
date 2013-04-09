<%
	String[] Data=new String[35];
	Data[0]="Anna";
	Data[1]="Boy";
	Data[2]="Cinderella";
	Data[3]="Dimit";
	Data[4]="Eva";
	Data[5]="France";
	Data[6]="Great";
	Data[7]="Horse";
	Data[8]="Italy";
	Data[9]="Jacob";
	Data[10]="Kite";
	Data[11]="Linda";
	Data[12]="Monkey";
	Data[13]="Neon";
	Data[14]="Amrit";
	Data[15]="Deepali";
	Data[16]="Neha";
	Data[17]="Mansi";
	Data[18]="Zebra";
	Data[19]="Australia";
	Data[20]="India";
	Data[21]="Bharat";
	Data[22]="Zoo";
	Data[23]="Yen";
	Data[24]="Pound";
	Data[25]="North";
	Data[26]="East";
	Data[27]="West";
	Data[28]="Catty";
	Data[29]="Cat";
	Data[30]="aaa";
		Data[31]="diana";
			Data[32]="diya mirza";
				Data[33]="diwali masti";
				Data[34]="dimiy";

	String str=request.getParameter("str");
	if(str.length()>0){
		String hint="";
		for(int i=0;i<Data.length;i++){
			if(str.length()>Data[i].length()){
			hint="";
			}else if(str.equalsIgnoreCase(Data[i].substring(0,str.length()))){
				if(hint==""){
					hint=Data[i];
				}else{
					hint = hint+","+Data[i];
				}
			}					
		}
		if(hint==""){
			out.print("No Suggestions");
		}else{
		out.print(hint);
	}	
}
%>