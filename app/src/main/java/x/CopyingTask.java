package x;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyingTask extends AsyncTask<File, Integer, Integer> {
    private Context ctx;
    private ProgressDialog dialog;
    private boolean done = false;
    private int i = 0;
    private int max = 0;
    private File sourceLocation;
    private File targetLocation;

    public CopyingTask(Context context, File file, File file2) {
        this.ctx = context;
        this.dialog = new ProgressDialog(context);
        this.dialog.setProgressStyle(1);
        this.dialog.setCancelable(false);
        this.dialog.setIndeterminate(false);
        this.dialog.setTitle(Constant.backup);
        this.dialog.setMessage(Constant.backupm);
        this.sourceLocation = file;
        this.targetLocation = file2;
    }

    private void CountFiles(File file) {
        if (file.isDirectory()) {
            for (File CountFiles : file.listFiles()) {
                CountFiles(CountFiles);
            }
        }
        this.max++;
    }

    private void DeleteDirectory(File file) {
        if (file.isDirectory()) {
            for (File DeleteDirectory : file.listFiles()) {
                DeleteDirectory(DeleteDirectory);
            }
        }
        file.delete();
    }



    private void copyDirectory(File file, File file2) throws IOException{
        file.getPath();
        if (file.isDirectory()) {
            if (!file2.exists()) {
                file2.mkdir();
            } else {
                DeleteDirectory(file2);
                file2.mkdir();
            }
            String[] list = file.list();
            for (String s : list) {
                copyDirectory(new File(file, s), new File(file2, s));
            }
            return;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read <= 0) {
                fileInputStream.close();
                fileOutputStream.close();
                this.i++;
                publishProgress(this.i);
                return;
            }
            fileOutputStream.write(bArr, 0, read);
        }
    }


    public Integer doInBackground(File... fileArr) {
        try {
            copyDirectory(this.sourceLocation, this.targetLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.done = true;
        return null;
    }

    public void onPostExecute(Integer num) {
        this.dialog.dismiss();
        if (this.done) {
            Toast.makeText(this.ctx, Constant.backups, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.ctx, Constant.backupf, Toast.LENGTH_SHORT).show();
        }
    }

     public void onPreExecute() {
        CountFiles(this.sourceLocation);
        this.dialog.setMax(this.max);
        this.dialog.show();
    }

    public void onProgressUpdate(Integer... numArr) {
        this.dialog.setProgress(numArr[0]);
    }
}