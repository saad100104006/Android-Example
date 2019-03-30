package online.ghuri.com.beacondoctorapp.JournalDetail.ViewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.BlogSIngleTon;
import online.ghuri.com.beacondoctorapp.R;
import online.ghuri.com.beacondoctorapp.databinding.ActivityJournalListBinding;

public class JournalListFragment extends Fragment {
    public static final String JOURNAL_EXTRA_TAG = "journal_list_extra_tag";
    private ActivityJournalListBinding mBinding;
    private String mIssueName;


    public void initialFragment(String mIssueName){
        this.mIssueName=mIssueName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       mBinding=DataBindingUtil.inflate(inflater,R.layout.activity_journal_list,container,false);
        mBinding.toolbarTitle.setText(mIssueName);

        mBinding.recyclerBlog.setAdapter(new BlogAdapter());
        mBinding.recyclerBlog.setLayoutManager(new LinearLayoutManager(getActivity()));
        return mBinding.getRoot();
    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_journal_list);
         mIssueName=getIntent().getStringExtra(JOURNAL_EXTRA_TAG);


    *//*    PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);*//*








    }
*/


    class DownloadFile extends AsyncTask<String, Integer, Long> {


        private String targetFileName;
        private Context context;
        String strFolderName;
        private ProgressDialog mProgressDialog;
        private File folder;

        public DownloadFile(Context activity, String targetFileName) {

            this.context = activity;
            this.targetFileName = targetFileName;
        }

        public DownloadFile(Context activity) {

            this.context = activity;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(context);// Change Mainactivity.this with your activity name.
            mProgressDialog.setMessage("Downloading");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.show();
        }

        @Override
        protected Long doInBackground(String... aurl) {


            if (targetFileName == null)
                targetFileName = (String) aurl[0].substring(((String) aurl[0]).lastIndexOf("/"));

       /* if (targetFileName.equals("themesong2017")){
            targetFileName="themesong2017.mp3";
        }*/


            int count;
            try {
                URL url = new URL((String) aurl[0]);
                URLConnection conexion = url.openConnection();
                conexion.setRequestProperty("Accept-Encoding", "identity");
                conexion.connect();

                int lenghtOfFile = conexion.getContentLength();
                String PATH = Environment.getExternalStorageDirectory() + "/" + "Beacon" + "/";
                folder = new File(PATH);
                if (!folder.exists()) {
                    folder.mkdir();//If there is no folder it will be created.
                }
                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream(PATH + targetFileName);
                byte data[] = new byte[1024];
                double total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // Log.i("download_test",total+"");
                    // publishProgress((int)total);
                    double result = (total / lenghtOfFile) * 100;
                    Log.i("download_test", lenghtOfFile + "   " + result + "");
                    publishProgress((int) result);
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {
            mProgressDialog.setProgress(progress[0]);
            if (mProgressDialog.getProgress() == mProgressDialog.getMax()) {
                mProgressDialog.dismiss();
                Toast.makeText(context, "File Downloaded", Toast.LENGTH_SHORT).show();

                File file = new File( Environment.getExternalStorageDirectory() + "/" + "pdf/" + targetFileName);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);


            }
        }

        protected void onPostExecute(String result) {


        }


    }



    public class BlogViewHolder extends RecyclerView.ViewHolder{
        TextView fileTv;
        View view;
        ImageView blogImage;
        public BlogViewHolder(View itemView) {
            super(itemView);
            fileTv=itemView.findViewById(R.id.filename_tv);
            view=itemView;
            blogImage=itemView.findViewById(R.id.imageView2);
        }
    }


    public class BlogAdapter extends RecyclerView.Adapter<BlogViewHolder>{

        List<online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Blog>mBlogs;

        public BlogAdapter() {

            mBlogs= BlogSIngleTon.getInstance().getmBlogList();
        }

        @Override
        public BlogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View view= LayoutInflater.from(getActivity()).inflate(R.layout.row_blog,parent,false);
            return new BlogViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BlogViewHolder holder, int position) {
           final online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Blog blog=mBlogs.get(position);
            holder.fileTv.setText(blog.getName());

            File file = new File( Environment.getExternalStorageDirectory() + "/" + "Beacon/" + blog.getFile());
            if (file.exists()){
                holder.blogImage.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.ic_open_in_browser_black_36dp));
            }else {
                holder.blogImage.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.ic_file_download_black_36dp));

            }

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    File file = new File( Environment.getExternalStorageDirectory() + "/" + "Beacon/" + blog.getFile());
                    if (file.exists()){
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);

                    }else {
                        String fileUrl="http://flexibac.com.bd/public/pdf/"+blog.getFile();
                        new DownloadFile(getActivity()).execute(fileUrl);
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return mBlogs.size();
        }
    }

}


