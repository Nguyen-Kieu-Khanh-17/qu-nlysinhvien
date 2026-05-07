# Hệ thống Quản lý Sinh viên - Spring Boot & SQL Server

Dự án Quản lý Sinh viên hoàn thiện với đầy đủ các tính năng CRUD, xuất báo cáo và quản lý ảnh đại diện.

## 🚀 Tính năng chính
- **Quản lý Sinh viên**: Thêm, sửa, xóa, tìm kiếm sinh viên.
- **Ảnh đại diện (Avatar)**: Hỗ trợ tải lên và hiển thị ảnh đại diện cho từng sinh viên.
- **Xuất dữ liệu**: Xuất danh sách sinh viên ra file **PDF** và **Excel** chuyên nghiệp.
- **Dashboard**: Thống kê nhanh tổng số lượng sinh viên và độ tuổi trung bình.
- **Giao diện hiện đại**: Sử dụng HTML/CSS/JS thuần với thiết kế chuyên nghiệp, responsive.
- **Backend mạnh mẽ**: Xây dựng trên Spring Boot 3, JPA Hibernate và SQL Server.

## 🛠 Hướng dẫn cài đặt

### 1. Yêu cầu hệ thống
- Java 17 trở lên.
- SQL Server.
- Maven.

### 2. Cấu hình Cơ sở dữ liệu
Chỉnh sửa file `src/main/resources/application.properties` để khớp với thông tin SQL Server của bạn:
```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=school;...
spring.datasource.username=sa
spring.datasource.password=123456
```

### 3. Chạy ứng dụng
Mở terminal tại thư mục gốc và chạy:
```bash
mvn spring-boot:run
```
Sau đó truy cập: `http://localhost:8080`

## 📸 Ảnh chụp màn hình
Hệ thống bao gồm bảng danh sách, form nhập liệu và các nút xuất file tiện lợi.

---
*Dự án được phát triển bởi Antigravity AI.*
