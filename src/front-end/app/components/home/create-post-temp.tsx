export default function CreatePostTemp() {
  return (
    <div className="flex items-center justify-center h-full w-full left-0 top-0">
      <div className="relative w-[1000px] h-[856px] bg-white rounded-[15px]">
        <div className="flex items-center justify-between h-[50px] mx-[10px]">
          <p
            onClick={() => { }}
            className="text-xl text-[#b0b0b0] font-bold hover:cursor-pointer"
          >
            Cancel
          </p>
          <p className="text-[1.625rem] text-black font-bold">Create new post</p>
          <p
            onClick={() => { }}
            className="text-xl text-[#0195F7] font-bold hover:cursor-pointer"
          >
            Create
          </p>
        </div>
        <hr className="border border-[#b0b0b0]" />
        <div className="grid gap-[15px] w-[960px] h-[771px] ml-[20px] mt-[15px]">
          <div className="w-[960px]">
            <p className="ml-[7px] text-[1.1875rem] text-black font-bold">Content</p>
            <textarea
              name="content"
              placeholder="Type something here..."
              className="w-full h-[350px] p-[10px] text-black border border-[#b0b0b0] rounded-[15px] resize-none"
            />
          </div>
          <div>
            <label>
              <div
                style={{
                  fontWeight: 'bold',
                  color: '#000000'
                }}
              >Tags</div>
              <input
                type="text"
                name="tags"
                placeholder="Add tags"
                style={{
                  width: '100%',
                  padding: '8px',
                  margin: '10px 0',
                  border: '1px solid #949494',
                  borderRadius: '13px',
                  backgroundColor: '#ffffff',
                  color: '#000000'
                }}
              />
            </label>
          </div>
          <div>
            <label>
              <div style={{ fontWeight: 'bold', color: '#000000' }}>Media</div>
              <input
                type="file"
                name="media"
                accept="image/*,video/*"
                style={{ display: 'none' }}
                id="fileInput"
                multiple
              />
              <div
                style={{
                  position: 'relative',
                  border: '1px solid #949494',
                  borderRadius: '13px',
                  padding: '10px',
                  display: 'flex',
                  flexWrap: 'wrap',
                  minHeight: '200px',
                }}
              >
              </div>
            </label>
          </div>
        </div>
      </div>
    </div>
  );
}