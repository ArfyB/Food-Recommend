// 비밀번호의 길이가 12자리 이상인가?
function validateUserPwd1(pwd)
{
	return pwd.length >= 12;
}

// 비밀번호에 공백이 포함되어 있는가?
function validateUserPwd2(pwd)
{
	return pwd.indexOf(" ") === -1 && pwd === pwd.trim();
}

// 비밀번호에 최소 1개의 특수문자가 포함되어 있는가?
function validatePwdSpecialChar(pwd) {
	var specialCharPattern = /[!@#$%^&*(),.?":{}|<>]/;
	return specialCharPattern.test(pwd);
}

// 비밀번호와 비밀번호 확인이 동일하게 입력되었는가?
function PwdCheck(pwd, check)
{
	return pwd === check;	
}

// 닉네임에 공백이 없고 2글자이상 16글자 이하인가?
function NCheck(nick)
{
	return nick === nick.trim() && nick.length >= 2 && nick.length <= 16;
}

var PwdCheckText = $("#PwdCheckText");
var NickCheckText = $("#NickCheckText");
var TagCheckText = $("#TagCheckText");

$(function()
{
	$('#join').on('click', function(event)
	{
		// 이메일, 비밀번호, 닉네임, 식별태그 가져오기 
		var Form = $('#UserData');
		var UserData = new FormData(Form[0]);
		
		var Pwd1 = $("#UserPwd1").val();
		var Pwd2 = $("#UserPwd2").val();
		var Nick = $("#UserNick").val();
		
		if(!validateUserPwd1(Pwd1))
    	{
			PwdCheckText.text('비밀번호는 12글자 이상');
			return;
		}
		
		else if(!validateUserPwd2(Pwd1))
    	{
			PwdCheckText.text('비밀번호는 공백이 포함될 수 없음');
			return;
		}
		
		else if (!validatePwdSpecialChar(Pwd1)) {
			PwdCheckText.text('비밀번호는 특수문자가 1개이상 포함');
			return;
		}
		
		else if(!PwdCheck(Pwd1, Pwd2))
		{
			PwdCheckText.text('비밀번호가 다릅니다');
			return;
		}
		
		else if(!NCheck(Nick))
		{
			NickCheckText.text('닉네임은 2글자이상 16글자 이하입니다');
			return;
		}
		
		PwdCheckText.text("");
		NickCheckText.text("");
		
		
		$.ajax
		({
			type : 'post',
			url : '/sec/tag',
			data : UserData,
			processData : false,
			contentType : false,
			cache : false,
			success : function(res)
			{
				if (res.check)
				{
					$.ajax
					({
						type : 'post',
						url : '/sec/join',
						data : UserData,
						processData : false,
						contentType : false,
						cache : false,
						success : function(res)
						{
							if (res.join)
							{
								alert("가입 완료!");
								location.href="/food/mainpage";	
							}
							else
							{
								alert("가입 실패");
							}
						},
						error : function(e)
						{
							console.log(e);
						}
					})
				}
				else
				{
					TagCheckText.text('사용할 수 없는 태그 입니다');
				}
			},
			error : function(e)
			{
				console.log(e);
			}
		});
		
	})
})