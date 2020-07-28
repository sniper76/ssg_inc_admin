ClassicEditor
	.create( document.querySelector( '#contents' ), {
		//toolbar: [ 'heading', '|', 'bold', 'italic', 'link' ],
		toolbar: ['undo', 'redo', 'bold', 'italic', 'blockQuote', 'heading', '|', 'link', 'numberedList', 'bulletedList', 'mediaEmbed'],
	
		language: 'ko',        
		ckfinder: {
			uploadUrl: '/upload/ckeditorupload'
		}
		  
	} )
	.then( editor => {
		window.editor = editor;
	} )
	.catch( err => {
		console.error( err.stack );
	} );