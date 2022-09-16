var input = document.getElementById("searchTerm");

let searchSocket = new WebSocket("ws://localhost:9000/socket");

function search() {
	
	var searchKey = document.getElementById("searchTerm").value;
	let message = {
		"keyword": searchKey
	};
	let msg = JSON.stringify(message);	
	searchSocket.send(msg);
	
	
	searchSocket.onmessage = function(event) {
	
		var div = document.getElementById('result_contents');
		var child_div = document.createElement('div');
		
		var response = event.data;
		const Res = JSON.parse(response);
		const data = Res.data;
		let keys = Object.keys(data).reverse();
		console.log(keys);
		
		for(let i=0; i < keys.length; i++)
		{
		console.log(data[keys[i]].length);
			for(let j=0; j<data[keys[i]].length; j++)
			{
				var ResObj = data[keys[i]][j];
				var inner = '<div class="result-div">'
				inner += "<div>"
				inner += '<p> User: ' + '<a href="http://localhost:9000/user/' + ResObj.login + '" target="_blank">' + ResObj.login+ '</a> </p>';
				inner += '<p> Repository: ' + '<a href="http://localhost:9000/check/' + ResObj.id + '" target="_blank">' + ResObj.repoName+ '</a> </p>';
				inner += "<p> Topics: </p>";
				
				const ResTopics = ResObj.topics;
				for( let j = 0; j< ResTopics.length; j++)
				{
				 inner += '<li>' + '<a href="http://localhost:9000/topicsearch/' + ResTopics[j] + '" >'  + ResTopics[j] + '</a> </li>';
				}	
				
				inner += " </div> </div>"
				child_div.innerHTML += inner;
				console.log(data[keys[i]][j].id);
				console.log(data[keys[i]][j].login);
			}
		}
		
		div.innerHTML= "";
		div.prepend(child_div);	
	}
}