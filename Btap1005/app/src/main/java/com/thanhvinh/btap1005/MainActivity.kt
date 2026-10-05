package com.thanhvinh.btap1005

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.thanhvinh.btap1005.databinding.ActivityMainBinding // Thay bằng package của bạn

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Khai báo một hằng số dùng làm Key để gửi/nhận dữ liệu Intent
    companion object {
        const val EXTRA_NAME = "EXTRA_NAME"
    }

    // Đăng ký bộ lắng nghe kết quả trả về từ EditActivity
    private val editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Kiểm tra xem EditActivity có trả về kết quả thành công không (RESULT_OK)
        if (result.resultCode == Activity.RESULT_OK) {
            // Lấy dữ liệu Intent trả về
            val data: Intent? = result.data
            val newName = data?.getStringExtra(EXTRA_NAME)

            // Cập nhật lên giao diện
            if (!newName.isNullOrEmpty()) {
                binding.tvName.text = newName
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bắt sự kiện bấm nút Chỉnh sửa
        binding.btnEdit.setOnClickListener {
            // Tạo Intent để chuyển sang EditActivity
            val intent = Intent(this, EditActivity::class.java)

            // Đính kèm tên hiện tại gửi sang EditActivity
            val currentName = binding.tvName.text.toString()
            intent.putExtra(EXTRA_NAME, currentName)

            // Khởi chạy Intent để chờ kết quả trả về
            editLauncher.launch(intent)
        }
    }
}