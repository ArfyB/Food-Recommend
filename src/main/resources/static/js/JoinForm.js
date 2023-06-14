$(function()
{
	$('#join').on('click', function(event)
	{
		var form = $('#UserEmail');
		var UserData = new FormData(form);
		
		$.ajax
		({
			type : 'post',
			url : '/sec/email',
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