import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.l3full.R

class StudentsAdapter(
    private val students: List<Student>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick()
    }

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(student: Student) {
            nameTextView.text = student.name
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int {
        return students.size
    }
}
