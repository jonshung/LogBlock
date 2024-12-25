"use client";
import { useState } from "react";

let openDialogExternal: () => void;

export default function CreatePost() {
  const [isDialogOpen, setDialogOpen] = useState(false);
  const [postData, setPostData] = useState({
    content: '',
    tags: '',
  });
  const [isConfirmDialogOpen, setIsConfirmDialogOpen] = useState(false);
  const [files, setFiles] = useState<File[]>([]);
  const [showMediaPlaceholder, setShowMediaPlaceholder] = useState(true);


  const openDialog = () => setDialogOpen(true);
  const closeDialog = () => setDialogOpen(false);
  const handleCancel = () => {
    setIsConfirmDialogOpen(true);
  };
  const handleConfirmDelete = () => {
    setPostData({
      content: '',
      tags: '',
    });
    setFiles([]);
    setDialogOpen(false);
    setShowMediaPlaceholder(true);
    setIsConfirmDialogOpen(false);
  };
  const handleCloseDialog = () => {
    setIsConfirmDialogOpen(false);
  };
  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setPostData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };
  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFiles = e.target.files;
    if (selectedFiles && selectedFiles.length > 0) {
      setFiles((prevFiles) => [...prevFiles, ...Array.from(selectedFiles)]);
      setShowMediaPlaceholder(false);
    }
  };


  const handleRemoveFile = (fileToRemove: File) => {
    const updatedFiles = files.filter(file => file !== fileToRemove);
    setFiles(updatedFiles);
    if (updatedFiles.length === 0) {
      setShowMediaPlaceholder(true);
    }
  };

  const createPost = () => {
    console.log('Post created:', postData);
    setPostData({
      content: '',
      tags: '',
    });
    setFiles([]);
    setShowMediaPlaceholder(true);
    closeDialog();
  };



  openDialogExternal = openDialog;

  return (
    <div>
      {isDialogOpen && (
        <div
          style={{
            position: 'fixed',
            top: 0,
            left: 0,
            width: '100%',
            height: '100%',
            backgroundColor: 'rgba(0, 0, 0, 0.5)',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            zIndex: 1000
          }}
        >
          <div
            style={{
              backgroundColor: 'white',
              padding: '20px',
              borderRadius: '10px',
              width: '40%',
              height: '80%',
              position: 'relative',
            }}
          >
            <div style={{ display: 'flex', justifyContent: 'space-between' }}>

              <button
                onClick={handleCancel}
                style={{
                  backgroundColor: '#ffffff',
                  color: '#c7c9c8',
                  padding: '10px 20px',
                  border: 'none',
                  borderRadius: '16px',
                  cursor: 'pointer',
                  fontWeight: 'bold'
                }}
              >
                Cancel
              </button>

              <h2
                style={{
                  display: 'flex',
                  justifyContent: 'center',
                  alignContent: 'center',
                  fontSize: '20px',
                  color: '#000000',
                  fontWeight: 'bold'
                }}
              >
                Create new post</h2>

              <button
                onClick={createPost}
                style={{
                  backgroundColor: '#ffffff',
                  color: 'blue',
                  padding: '10px 20px',
                  border: 'none',
                  borderRadius: '16px',
                  cursor: 'pointer',
                  fontWeight: 'bold',
                }}
              >
                Create
              </button>

            </div>

            <hr style={{ border: '1px solid #ccc', marginTop: '10px', marginBottom: '10px' }} />

            <div>
              <label>
                <div
                  style={{
                    fontWeight: 'bold',
                    color: '#000000'

                  }}
                >Content</div>
                <textarea
                  name="content"
                  value={postData.content}
                  onChange={handleChange}
                  placeholder="Enter post content"
                  style={{
                    width: '100%',
                    height: '100px',
                    padding: '8px',
                    margin: '10px 0',
                    border: '1px solid #949494',
                    borderRadius: '12px',
                    backgroundColor: '#ffffff',
                    color: '#000000'
                  }}
                ></textarea>
              </label>
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
                  value={postData.tags}
                  onChange={handleChange}
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
                  onChange={handleFileChange}
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
                    justifyContent: showMediaPlaceholder ? 'center' : 'flex-start',
                    alignItems: showMediaPlaceholder ? 'center' : 'flex-start',
                    flexWrap: 'wrap',
                    minHeight: '200px',
                    backgroundColor: showMediaPlaceholder ? 'transparent' : '#f9f9f9',
                  }}
                >
                  {showMediaPlaceholder ? (
                    <div style={{ display: 'grid' }}>
                      <img
                        style={{
                          marginTop: '10px',
                          height: '200px',
                          width: '220px',
                        }}
                        src="/album.png"
                        alt="album"
                      />
                      <div
                        style={{
                          color: '#000000',
                          fontWeight: 'bold',
                          margin: '10px',
                          fontSize: '15px',
                        }}
                      >
                        Drag photos/videos/files here
                      </div>
                      <button
                        onClick={() => document.getElementById('fileInput')?.click()}
                        style={{
                          padding: '8px 16px',
                          border: '1px solid #2191ed',
                          borderRadius: '16px',
                          backgroundColor: '#2191ed',
                          color: '#fff',
                          cursor: 'pointer',
                          marginBottom: '20px',
                        }}
                      >
                        Select from computer
                      </button>
                    </div>
                  ) : (
                    files.map((file, index) => (
                      <div key={index} style={{ position: 'relative' }}>
                        <img
                          src={URL.createObjectURL(file)}
                          alt={file.name}
                          style={{
                            margin: '10px',
                            height: '100px',
                            width: '100px',
                            objectFit: 'cover',
                            borderRadius: '8px',
                          }}
                        />
                        <button
                          onClick={() => handleRemoveFile(file)}
                          style={{
                            padding: '5px 10px',
                            borderRadius: '50%',
                            backgroundColor: '#595856',
                            color: '#cfcdca',
                            border: 'none',
                            cursor: 'pointer',
                            fontWeight: 'bold',
                            fontSize: '16px',
                            position: 'absolute',
                            top: '-5px',
                            right: '-5px',
                          }}
                        >
                          X
                        </button>
                      </div>
                    ))
                  )}
                </div>
              </label>

            </div>
          </div>
        </div>
      )}
      {isConfirmDialogOpen && (
        <div
          style={{
            position: 'fixed',
            top: 0,
            left: 0,
            width: '100%',
            height: '100%',
            backgroundColor: 'rgba(0, 0, 0, 0.5)',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            zIndex: 1000,
          }}
        >
          <div
            style={{
              backgroundColor: 'white',
              padding: '20px',
              borderRadius: '10px',
              width: '30%',
              textAlign: 'center',
            }}
          >
            <div style={{ marginTop: '20px', marginBottom: '10px', color: '#000000', fontWeight: 'bold', fontSize: '26px' }}
            >Discard post?</div>
            <div style={{ color: '#000000', fontWeight: 'bold' }}>If you leave, your edits won&apost be saved.</div>
            <hr style={{ border: '1px solid #ccc', marginTop: '40px' }} />
            <div style={{ textAlign: 'center' }}>
              <button
                onClick={handleConfirmDelete}
                style={{
                  backgroundColor: '#ffffff',
                  color: 'red',
                  padding: '10px 20px',
                  border: 'none',
                  borderRadius: '0px',
                  cursor: 'pointer',
                  width: '100%',
                  height: '100%',
                  fontWeight: 'bold',

                }}
                onMouseEnter={(e) => (e.currentTarget.style.background = '#f0ede4')}
                onMouseLeave={(e) => (e.currentTarget.style.background = '#ffffff')}
              >
                Discard
              </button>

              <hr style={{ border: '1px solid #ccc' }} />

              <button
                onClick={handleCloseDialog}
                style={{
                  backgroundColor: '#ffffff',
                  color: '#000000',
                  padding: '10px 20px',
                  border: 'none',
                  borderRadius: '0px',
                  cursor: 'pointer',
                  width: '100%',
                  height: '100%',
                  fontWeight: 'bold',
                }}
                onMouseEnter={(e) => (e.currentTarget.style.background = '#f0ede4')}
                onMouseLeave={(e) => (e.currentTarget.style.background = '#ffffff')}
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}

    </div>
  );
}

export const triggerOpenDialog = () => {
  if (openDialogExternal) {
    openDialogExternal();
  } else {
    console.warn("Post component cannot use!");
  }
};
