package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import org.pytorch.IValue;
import org.pytorch.LiteModuleLoader;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Model {
    private static String MODEL_NAME = "big_model2.ptl";
    Context context;
    Module module;

    public Model(Context context){
        this.context = context;
    }
    public void init() throws IOException{
        module = LiteModuleLoader.load(fetchModelFile(context, MODEL_NAME));
    }

    public Tensor recognize(Bitmap image){
        Tensor input =  TensorImageUtils.bitmapToFloat32Tensor(
                image,
                TensorImageUtils.TORCHVISION_NORM_MEAN_RGB,
                TensorImageUtils.TORCHVISION_NORM_STD_RGB);
        Tensor output = module.forward(IValue.from(input)).toTensor();

        return output;
    }

    public static String fetchModelFile(Context context, String modelName) throws IOException {
        File file = new File(context.getFilesDir(), modelName);
        if (file.exists() && file.length() > 0) {
            return file.getAbsolutePath();
        }

        try (InputStream is = context.getAssets().open(modelName)) {
            try (OutputStream os = new FileOutputStream(file)) {
                byte[] buffer = new byte[4 * 1024];
                int read;
                while ((read = is.read(buffer)) != -1) {
                    os.write(buffer, 0, read);
                }
                os.flush();
            }
            Log.d("파일경로 : ", file.getAbsolutePath());
            return file.getAbsolutePath();
        }

    }
}
