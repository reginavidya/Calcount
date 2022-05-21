import android.content.Intent
import androidx.fragment.app.Fragment
import org.d3if2126.calcount.MainViewModel
import org.d3if2126.calcount.R
import org.d3if2126.calcount.databinding.FragmentHitungBinding
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if2126.calcount.model.HasilDiskon

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_hitungFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button1.setOnClickListener { hitungDiskon()}
        binding.button2.setOnClickListener { reset()}
        binding.button3.setOnClickListener { keluar()}
        binding.shareButton.setOnClickListener { shareData() }
            viewModel.getHasilDiskon().observe(requireActivity(), { showResult(it) })
    }
    private fun shareData() {
        val message = getString(R.string.bagikan_template,
            binding.editHarga.text,
            binding.editDiskon.text,
            binding.editHasil.text,
            binding.editTotal.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
                    startActivity(shareIntent)
        }
    }
    private fun hitungDiskon(){
        val harga = binding.editHarga.text.toString()
        if (TextUtils.isEmpty(harga)) {
            Toast.makeText(context, R.string.harga_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val diskon = binding.editDiskon.text.toString()
        if (TextUtils.isEmpty(diskon)) {
            Toast.makeText(context, R.string.diskon_invalid, Toast.LENGTH_LONG).show()
            return
        }
         viewModel.hitungDiskon(
            harga.toFloat(),
            diskon.toFloat()
        )
    }
    private fun showResult(result: HasilDiskon?) {
        if (result == null) return

        binding.editHasil.setText("Rp."+ result.hitungDiskon.toInt())
        binding.editTotal.setText("Rp."+ result.totalDiskon.toInt())
    }
    private fun reset(){

        binding.editHarga.setText("")
        binding.editDiskon.setText("")
        binding.editHasil.setText("")
        binding.editTotal.setText("")

    }
    private fun keluar(){
        android.os.Process.killProcess(android.os.Process.myPid())
    }
}