package id.ac.umn.testingfirebase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import id.ac.umn.testingfirebase.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        binding.idTxt.text = currentUser?.uid
        binding.nameTxt.text = currentUser?.displayName
        binding.emailTxt.text = currentUser?.email

        Glide.with(this).load(currentUser?.photoUrl).into(binding.profileImage)

        binding.signOutBtn.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}


