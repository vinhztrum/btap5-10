package com.thanhvinh.btap1005
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thanhvinh.btap1005.databinding.ActivityEditBinding // Thay bằng package của bạn

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Nhận tên hiện tại từ MainActivity và điền sẵn vào EditText
        val currentName = intent.getStringExtra(MainActivity.EXTRA_NAME)
        // Nếu không phải là chữ mặc định thì mới điền vào
        if (currentName != "Chưa có thông tin") {
            binding.edtName.setText(currentName)
        }

        // 2. Xử lý nút Lưu & Quay lại
        binding.btnSave.setOnClickListener {
            // Lấy tên mới người dùng vừa nhập
            val newName = binding.edtName.text.toString()

            // Tạo một Intent rỗng chỉ để chứa dữ liệu trả về
            val resultIntent = Intent()
            resultIntent.putExtra(MainActivity.EXTRA_NAME, newName)

            // Đóng gói kết quả (Báo thành công là RESULT_OK và nhét resultIntent vào)
            setResult(Activity.RESULT_OK, resultIntent)

            // Đóng EditActivity và tự động quay về MainActivity
            finish()
        }
    }
}