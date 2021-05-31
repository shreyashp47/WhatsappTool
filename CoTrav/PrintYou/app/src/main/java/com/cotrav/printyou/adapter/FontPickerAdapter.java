package com.cotrav.printyou.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cotrav.printyou.R;

import java.util.ArrayList;
import java.util.List;


public class FontPickerAdapter extends RecyclerView.Adapter<FontPickerAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Typeface> fontPickerFonts;
    private OnFontPickerClickListener onFontPickerClickListener;

    FontPickerAdapter(@NonNull Context context, @NonNull List<Typeface> fontPickerFonts) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.fontPickerFonts = fontPickerFonts;
    }

    public FontPickerAdapter(@NonNull Context context) {
        this(context, getDefaultColors(context));
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.font_picker_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.fontPickerView.setTypeface(fontPickerFonts.get(position));
    }

    @Override
    public int getItemCount() {
        return fontPickerFonts.size();
    }

    private void buildFontPickerView(View view, int colorCode) {
        view.setVisibility(View.VISIBLE);

        ShapeDrawable biggerCircle = new ShapeDrawable(new OvalShape());
        biggerCircle.setIntrinsicHeight(20);
        biggerCircle.setIntrinsicWidth(20);
        biggerCircle.setBounds(new Rect(0, 0, 20, 20));
        biggerCircle.getPaint().setColor(colorCode);

        ShapeDrawable smallerCircle = new ShapeDrawable(new OvalShape());
        smallerCircle.setIntrinsicHeight(5);
        smallerCircle.setIntrinsicWidth(5);
        smallerCircle.setBounds(new Rect(0, 0, 5, 5));
        smallerCircle.getPaint().setColor(Color.WHITE);
        smallerCircle.setPadding(10, 10, 10, 10);
        Drawable[] drawables = {smallerCircle, biggerCircle};

        LayerDrawable layerDrawable = new LayerDrawable(drawables);

        view.setBackgroundDrawable(layerDrawable);
    }

    public void setOnFontPickerClickListener(OnFontPickerClickListener onFontPickerClickListener) {
        this.onFontPickerClickListener = onFontPickerClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView fontPickerView;

        public ViewHolder(View itemView) {
            super(itemView);
            fontPickerView = itemView.findViewById(R.id.font_picker_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onFontPickerClickListener != null)
                        onFontPickerClickListener.onFontPickerClickListener(fontPickerFonts.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnFontPickerClickListener {
        void onFontPickerClickListener(Typeface typeface);
    }

    public static List<Typeface> getDefaultColors(Context context) {
        ArrayList<Typeface> fontPickerFonts = new ArrayList<>();
        fontPickerFonts.add(Typeface.SERIF);
        fontPickerFonts.add(Typeface.MONOSPACE);
        fontPickerFonts.add(Typeface.SANS_SERIF);
        fontPickerFonts.add(Typeface.createFromAsset(context.getAssets(), "fonts/AlexBrush-Regular.ttf"));
        fontPickerFonts.add(Typeface.createFromAsset(context.getAssets(), "fonts/AmaticSC-Regular.ttf"));
        fontPickerFonts.add(Typeface.createFromAsset(context.getAssets(), "fonts/Pacifico.ttf"));
        fontPickerFonts.add(Typeface.createFromAsset(context.getAssets(), "fonts/SEASR.ttf"));
        fontPickerFonts.add(Typeface.createFromAsset(context.getAssets(), "fonts/ostrich-regular.ttf"));





        return fontPickerFonts;
    }
}
