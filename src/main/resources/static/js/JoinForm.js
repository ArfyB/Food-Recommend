$(function()
{
	$('#join').on('click', function(event)
	{
		var Form = $('#FUser');
		var UserData = new FormData(Form[0]);
		
		var EmailCheck = $("#EmailCheck");
		
		$.ajax
		({
			type : 'post',
			url : '/mail/send',
			data : UserData,
			processData : false,
			contentType : false,
			cache : false,
			success : function(res)
			{
				if (!res.check)
				{
					EmailCheck.text('');
					alert(res.certify? '메일이 전송 되었습니다' : '메일 전송에 실패 하였습니다');	
				}
				else
				{
					EmailCheck.text('이미 존재하는 계정입니다');
				}
			},
			error : function(e)
			{
				console.log(e);
			}
		})
	})
})