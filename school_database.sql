-- 1. Sử dụng Database 'school' (Hãy đảm bảo bạn đã tạo Database tên 'school' trước đó)
USE [school];
GO

-- 2. Xóa bảng 'students' cũ nếu tồn tại để tránh xung đột
IF OBJECT_ID('dbo.students', 'U') IS NOT NULL
    DROP TABLE dbo.students;
GO

-- 3. Tạo bảng 'students' với cấu trúc chuẩn
CREATE TABLE dbo.students (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    age INT NOT NULL,
    email NVARCHAR(255) NOT NULL UNIQUE,
    avatar NVARCHAR(255) NULL
);
GO

-- 4. Chèn dữ liệu mẫu tiếng Việt cực kỳ đẹp và chuyên nghiệp
INSERT INTO dbo.students (name, age, email, avatar) VALUES
(N'Nguyễn Nhật Nam', 20, 'nhatnam@gmail.com', 'https://i.pravatar.cc/150?u=nam'),
(N'Lê Thu Hà', 21, 'thuha@gmail.com', 'https://i.pravatar.cc/150?u=ha'),
(N'Trần Minh Quân', 22, 'minhquan@gmail.com', 'https://i.pravatar.cc/150?u=quan');
GO

-- 5. Truy vấn kiểm tra dữ liệu sau khi chèn
SELECT * FROM dbo.students;
GO
