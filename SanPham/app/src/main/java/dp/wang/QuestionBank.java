package dp.wang;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    public static List<Question> getQuestionsByTopic(String topic) {
        List<Question> list = new ArrayList<>();

        switch (topic) {
            case "Java":
                list.add(new Question("Java là ngôn ngữ?", new String[]{"Thông dịch", "Biên dịch", "Vừa biên dịch vừa thông dịch", "Không phải ngôn ngữ"}, 2));
                list.add(new Question("Từ khóa nào dùng để khai báo lớp?", new String[]{"define", "struct", "class", "object"}, 2));
                list.add(new Question("Kiểu dữ liệu nào là số nguyên?", new String[]{"float", "boolean", "int", "String"}, 2));
                list.add(new Question("Từ khóa để tạo đối tượng mới là gì?", new String[]{"object", "create", "init", "new"}, 3));
                list.add(new Question("Gói mặc định trong Java là gì?", new String[]{"java.io", "java.util", "java.lang", "java.net"}, 2));
                list.add(new Question("Phương thức khởi tạo là gì?", new String[]{"constructor", "initializer", "method", "builder"}, 0));
                list.add(new Question("Java có hỗ trợ đa kế thừa không?", new String[]{"Có", "Không", "Chỉ khi dùng interface", "Chỉ khi dùng abstract"}, 2));
                list.add(new Question("Từ khóa nào dùng để kế thừa lớp?", new String[]{"inherit", "extends", "super", "implements"}, 1));
                list.add(new Question("Interface là?", new String[]{"Lớp cụ thể", "Giao diện", "Biến toàn cục", "Hàm nội tại"}, 1));
                list.add(new Question("Hàm main có chữ ký nào?", new String[]{"void start()", "public void main()", "public static void main(String[] args)", "main(String[] args)"}, 2));
                list.add(new Question("int x = 5 / 2 kết quả là?", new String[]{"2.5", "2", "3", "5"}, 1));
                list.add(new Question("Kiểu dữ liệu boolean có giá trị nào?", new String[]{"1, 0", "true, false", "yes, no", "on, off"}, 1));
                list.add(new Question("System.out.println() dùng để?", new String[]{"Nhập dữ liệu", "In ra màn hình", "Tạo đối tượng", "Lưu file"}, 1));
                list.add(new Question("Vòng lặp nào kiểm tra điều kiện sau?", new String[]{"for", "while", "do...while", "foreach"}, 2));
                list.add(new Question("break dùng để?", new String[]{"Tạm dừng", "Tiếp tục vòng lặp", "Thoát vòng lặp", "Gọi hàm"}, 2));
                break;

            case "Python":
                list.add(new Question("Python là ngôn ngữ?", new String[]{"Biên dịch", "Thông dịch", "Hợp ngữ", "Không phải ngôn ngữ"}, 1));
                list.add(new Question("Từ khóa để định nghĩa hàm?", new String[]{"function", "fun", "def", "define"}, 2));
                list.add(new Question("Để in ra màn hình dùng?", new String[]{"print()", "echo()", "write()", "out()"}, 0));
                list.add(new Question("Kiểu dữ liệu list là?", new String[]{"tuple", "array", "list", "set"}, 2));
                list.add(new Question("Lệnh để lặp với chỉ số?", new String[]{"for i in range()", "while i", "loop", "repeat"}, 0));
                list.add(new Question("Python có phân biệt hoa thường không?", new String[]{"Có", "Không", "Tùy trường hợp", "Không rõ"}, 0));
                list.add(new Question("Số nguyên trong Python là?", new String[]{"int", "float", "number", "long"}, 0));
                list.add(new Question("Biến không cần khai báo kiểu trong Python?", new String[]{"Đúng", "Sai", "Tùy phiên bản", "Chỉ với số"}, 0));
                list.add(new Question("Thư viện vẽ đồ thị phổ biến?", new String[]{"math", "random", "matplotlib", "os"}, 2));
                list.add(new Question("Dấu nào để comment 1 dòng?", new String[]{"//", "#", "/*", "--"}, 1));
                list.add(new Question("Python chạy dòng lệnh nào đầu tiên?", new String[]{"main", "top", "__main__", "first"}, 2));
                list.add(new Question("Python viết bằng?", new String[]{"Java", "C", "Python", "Go"}, 1));
                list.add(new Question("Tuple khác list vì?", new String[]{"Không sắp xếp được", "Không thay đổi được", "Không lưu trữ được", "Không in ra được"}, 1));
                list.add(new Question("pip dùng để?", new String[]{"Tạo file", "Chạy chương trình", "Cài thư viện", "Xóa biến"}, 2));
                list.add(new Question("f-string dùng để?", new String[]{"Tạo hàm", "Tạo chuỗi định dạng", "Tạo số thực", "Tạo danh sách"}, 1));
                break;

            case "HTML":
                list.add(new Question("Thẻ tạo tiêu đề lớn nhất?", new String[]{"<title>", "<h6>", "<h1>", "<header>"}, 2));
                list.add(new Question("Thẻ tạo đoạn văn?", new String[]{"<div>", "<p>", "<section>", "<a>"}, 1));
                list.add(new Question("Thẻ chèn hình ảnh?", new String[]{"<img>", "<src>", "<pic>", "<link>"}, 0));
                list.add(new Question("Thẻ tạo liên kết?", new String[]{"<url>", "<link>", "<a>", "<href>"}, 2));
                list.add(new Question("Thuộc tính nào để chèn link hình ảnh?", new String[]{"href", "src", "link", "url"}, 1));
                list.add(new Question("HTML viết tắt của?", new String[]{"HighText Machine Language", "HyperText Markup Language", "HyperLink Text Mark", "None"}, 1));
                list.add(new Question("Thẻ tạo bảng?", new String[]{"<tab>", "<table>", "<grid>", "<list>"}, 1));
                list.add(new Question("Thẻ nào không cần thẻ đóng?", new String[]{"<p>", "<a>", "<img>", "<div>"}, 2));
                list.add(new Question("Thuộc tính để đặt tiêu đề trang?", new String[]{"<title>", "<header>", "<meta>", "<head>"}, 0));
                list.add(new Question("Thẻ <ul> dùng cho?", new String[]{"Danh sách có thứ tự", "Bảng", "Ảnh", "Danh sách không thứ tự"}, 3));
                list.add(new Question("Thẻ nào nằm trong <table>?", new String[]{"<tr>", "<ul>", "<div>", "<h1>"}, 0));
                list.add(new Question("Thẻ nào định nghĩa đầu trang?", new String[]{"<footer>", "<section>", "<header>", "<div>"}, 2));
                list.add(new Question("Thẻ tạo input trong form?", new String[]{"<form>", "<input>", "<entry>", "<text>"}, 1));
                list.add(new Question("Thẻ <strong> có tác dụng gì?", new String[]{"Làm nghiêng chữ", "Gạch dưới", "In đậm", "Gạch ngang"}, 2));
                list.add(new Question("Thẻ meta nằm trong?", new String[]{"<body>", "<footer>", "<head>", "<html>"}, 2));
                break;

            default:
                list.add(new Question("Không có câu hỏi cho chủ đề này", new String[]{"A", "B", "C", "D"}, 0));
                break;
        }

        return list;
    }
}
