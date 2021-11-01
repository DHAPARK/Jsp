/**
 * 
 */
	function sendit(){
		const joinform = document.joinform;
		//var checkText = document.getElementById("checkText");
		//if(joinform.userid.value == "" || joinform.userid.value==null){
			//alert("아이디를 입력하세요.")
			//joinform.userid.focus();
			//return false;
		//}
		//var checkText = document.getElementById("checkText");
		//if(checkText.innerHTML != "사용할 수 있는 아이디입니다."){
			//alert("아이디 중복검사를 먼저 해주세요");
			//return false;
		//}
		//if(joinform.userpw.value == "" || joinform.userpw.value == null){
			//alert("비밀번호를 입력하세요.");
			//joinform.userpw.focus();
			//return false;
		//}else{
			//A~Z,a~z,0~9,특수문자들이 포함되어있는 8 글자인지 검사하는 정규식
			//var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~!@#$%^*-]).{8,}$/;
			//var hangle = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
			//text가 그냥 검사 메서드라 하심 그리고 제대로 작성되어있다면 true값이 리턴된다하심
			//if(req.test(joinform.userpw.value)){
				//alert("비밀번호는 8자이상,숫자,대문자,소문자,특수문자를 모두 포함해야 합니다.");
				//return false;
				// 4개의 같은 문자열이 연속되어 있는지 검사하는 정규식
			//} else if(/(\w)\1\1\1/.test(joinform.userpw.value)){
				//alert("비밀번호는 같은 문자를 4번 이상 사용할 수 없습니다.");
				//return false;
				//공백 체크
			//} else if(joinform.userpw.value.search(/\s/)!=-1){
				//alert("공백은 들어갈 수 없습니다.");
				//return false;
			//} else if(hangle.test(joinform.userpw.value)){
				//alert("비밀번호에 한글을 사용할 수 없습니다.");
				//return false;
			//} 
		//}
		
		//if(joinform.username.value == "" || joinform.username.value == null){
			//alert("이름을 입력하세요.");
			//joinform.username.focus();
//			return false;
//		}
//		if(joinform.userphone.value == "" || joinform.userphone.value == null){
//			alert("핸드폰 번호를 입력하세요.");
//			joinform.userphone.focus();
//			return false;
//		}
//		if(joinform.postcode.value =="" || joinform.postcode.value==null){
//			alert("우편번호를 입력하세요");
//			joinform.findaddr.focus();
//			return false;
//		}
//		if(joinform.addr.value=="" || joinform.addr.value == null){
//			alert("주소를 입력하세요.");
//			joinform.findaddr.focus();
//			return false;
//		}
//		if(joinform.detailaddr.value == "" || joinform.detailaddr.value == null){
//			alert("상세주소를 입력하세요");
//			joinform.detailaddr.focus();
//			return false;
//		}
		joinform.submit();
	}

	//잉
	function checkId(){
		const useridTag = document.joinform.userid;
		//먼저 joinform dom안에  id가 userid인 태그를 가져오고
		var id = useridTag.value;//apple
		//useridTag의 value 즉 text값을 가져와 id변수에 넣는다.
		//checkText변수에 dom안의 id값이 checkText인 태그를 넣는다. 
		if(id==null || id==''){
			//만약 id값이 비어있거나 아직 작성하지 않은 상태이면
			//alert("아이디를 입력해주세요");
			//alert로 아이디를 입력해달라고 요구하고
			checkText.innerHTML="";
			//checkText의 안에도 중복인지 비교할게 없으니 공백으로 둔다.
		}else{
			var xhr = new XMLHttpRequest();
			/*여기서부턴 get방식의 Ajax
			//여기서부터 ajax의 사용이다. 일단 이 XMLHTTP어쩌구 객체는 원래 우리가 알게모르게 사용하던 앤데 서버의 통신을 위해서? ㅇㅇ 우리가 이번엔
					//우리 의도대로 조작하는 것이다.
			xhr.open("GET","checkId.jsp?userid="+id,true);
			//xhr.open 즉 어떠한 방식으로 페이지이동을 시켜주는 메서드 같다.
			//										이 true는 동기,비동기를 나눈다.
			xhr.send();
			//이거 아마 마지막에 전부 보낸다 하는 node.js문법같은데
			xhr.onreadystatechange = function(){
				//xhr.onreadystatechange변수안에   밑에 작성한 함수를 넣어준다.
				if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
					//만약 readyState변수의 상태가! XMLHttpRequest.DONE거나 xhr.status 가 200일때 밑으로 들어간다.
					if(xhr.responseText.trim()== "ok"){
						//일단 다른 홈페이지에서 응답이 올 건데  그 응답이 "ok"이면 innerHTML을 사용할수 있는 아이디로 채워주고
						checkText.innerHTML = "사용할 수 있는 아이디입니다."
					}else{
						//그 반대는 중복된 아이디로 취급
						checkText.innerHTML = "중복된 아이디입니다."
					}
				}
			}
			*/
			
			//여기부터는 POST 방식
			xhr.open("POST","/member/CheckId.me",true);
			xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xhr.send("userid="+id);			
			xhr.onreadystatechange = function(){
				//xhr.onreadystatechange변수안에   밑에 작성한 함수를 넣어준다.
				if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
					//만약 readyState변수의 상태가! XMLHttpRequest.DONE거나 xhr.status 가 200일때 밑으로 들어간다.
					if(xhr.responseText.trim()== "ok"){
						//일단 다른 홈페이지에서 응답이 올 건데  그 응답이 "ok"이면 innerHTML을 사용할수 있는 아이디로 채워주고
						checkText.innerHTML = "사용할 수 있는 아이디입니다."
					}else{
						//그 반대는 중복된 아이디로 취급
						checkText.innerHTML = "중복된 아이디입니다."
					}
				}
			}
			
		}
		//window.open("checkId.jsp?userid="+id);//checkId.jsp?userid=apple
	}
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
	