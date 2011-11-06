/*
 * Copyright (c) 2011 Skullab software @ Ivan Maruca
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.skullab.chess;

import java.util.ArrayList;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Displays a chessboard with (or without) notation around the board.
 * @author skullab.com
 * @attr rel com.skullab.chess.R.styleable.Chessaboard_enableNotation
 * @attr rel com.skullab.chess.R.styleable.Chessboard_whiteCellsColor 
 * @attr rel com.skullab.chess.R.styleable.Chessboard_blackCellsColor
 * @attr rel com.skullab.chess.R.styleable.Chessboard_whiteCellsBackground
 * @attr rel com.skullab.chess.R.styleable.Chessboard_blackCellsBackground
 * @attr rel com.skullab.chess.R.styleable.Chessboard_cellsSize
 * @attr rel com.skullab.chess.R.styleable.Chessboard_notationTextSize
 * @attr rel com.skullab.chess.R.styleable.Chessboard_notationTextColor
 * @attr rel com.skullab.chess.R.styleable.Chessboard_notationBackground
 * @attr rel com.skullab.chess.R.styleable.Chessboard_notationTypeface
 */
public class Chessboard extends LinearLayout {
	
	//private static final String TAG = Chessboard.class.getSimpleName() ;
	
	private LayoutInflater layoutInflater ;
	private View chessboard ;
	private OnCellClickListener mListener ;
	
	private String[] cellsName = {
			"a1","b1","c1","d1","e1","f1","g1","h1",
			"a2","b2","c2","d2","e2","f2","g2","h2",
			"a3","b3","c3","d3","e3","f3","g3","h3",
			"a4","b4","c4","d4","e4","f4","g4","h4",
			"a5","b5","c5","d5","e5","f5","g5","h5",
			"a6","b6","c6","d6","e6","f6","g6","h6",
			"a7","b7","c7","d7","e7","f7","g7","h7",
			"a8","b8","c8","d8","e8","f8","g8","h8"
	};
	private int[] notationId = {
			com.skullab.chess.R.id.notation_1_left,
			com.skullab.chess.R.id.notation_1_right,
			com.skullab.chess.R.id.notation_2_left,
			com.skullab.chess.R.id.notation_2_right,
			com.skullab.chess.R.id.notation_3_left,
			com.skullab.chess.R.id.notation_3_right,
			com.skullab.chess.R.id.notation_4_left,
			com.skullab.chess.R.id.notation_4_right,
			com.skullab.chess.R.id.notation_5_left,
			com.skullab.chess.R.id.notation_5_right,
			com.skullab.chess.R.id.notation_6_left,
			com.skullab.chess.R.id.notation_6_right,
			com.skullab.chess.R.id.notation_7_left,
			com.skullab.chess.R.id.notation_7_right,
			com.skullab.chess.R.id.notation_8_left,
			com.skullab.chess.R.id.notation_8_right,
			com.skullab.chess.R.id.notation_up,
			com.skullab.chess.R.id.notation_down
	} ;
	private int[] cellsId = {
		com.skullab.chess.R.id.a1,
		com.skullab.chess.R.id.b1,
		com.skullab.chess.R.id.c1,
		com.skullab.chess.R.id.d1,
		com.skullab.chess.R.id.e1,
		com.skullab.chess.R.id.f1,
		com.skullab.chess.R.id.g1,
		com.skullab.chess.R.id.h1,
		com.skullab.chess.R.id.a2,
		com.skullab.chess.R.id.b2,
		com.skullab.chess.R.id.c2,
		com.skullab.chess.R.id.d2,
		com.skullab.chess.R.id.e2,
		com.skullab.chess.R.id.f2,
		com.skullab.chess.R.id.g2,
		com.skullab.chess.R.id.h2,
		com.skullab.chess.R.id.a3,
		com.skullab.chess.R.id.b3,
		com.skullab.chess.R.id.c3,
		com.skullab.chess.R.id.d3,
		com.skullab.chess.R.id.e3,
		com.skullab.chess.R.id.f3,
		com.skullab.chess.R.id.g3,
		com.skullab.chess.R.id.h3,
		com.skullab.chess.R.id.a4,
		com.skullab.chess.R.id.b4,
		com.skullab.chess.R.id.c4,
		com.skullab.chess.R.id.d4,
		com.skullab.chess.R.id.e4,
		com.skullab.chess.R.id.f4,
		com.skullab.chess.R.id.g4,
		com.skullab.chess.R.id.h4,
		com.skullab.chess.R.id.a5,
		com.skullab.chess.R.id.b5,
		com.skullab.chess.R.id.c5,
		com.skullab.chess.R.id.d5,
		com.skullab.chess.R.id.e5,
		com.skullab.chess.R.id.f5,
		com.skullab.chess.R.id.g5,
		com.skullab.chess.R.id.h5,
		com.skullab.chess.R.id.a6,
		com.skullab.chess.R.id.b6,
		com.skullab.chess.R.id.c6,
		com.skullab.chess.R.id.d6,
		com.skullab.chess.R.id.e6,
		com.skullab.chess.R.id.f6,
		com.skullab.chess.R.id.g6,
		com.skullab.chess.R.id.h6,
		com.skullab.chess.R.id.a7,
		com.skullab.chess.R.id.b7,
		com.skullab.chess.R.id.c7,
		com.skullab.chess.R.id.d7,
		com.skullab.chess.R.id.e7,
		com.skullab.chess.R.id.f7,
		com.skullab.chess.R.id.g7,
		com.skullab.chess.R.id.h7,
		com.skullab.chess.R.id.a8,
		com.skullab.chess.R.id.b8,
		com.skullab.chess.R.id.c8,
		com.skullab.chess.R.id.d8,
		com.skullab.chess.R.id.e8,
		com.skullab.chess.R.id.f8,
		com.skullab.chess.R.id.g8,
		com.skullab.chess.R.id.h8
	};
	
	private ArrayList<Integer> whiteCellsId = new ArrayList<Integer>();
	private ArrayList<Integer> blackCellsId = new ArrayList<Integer>();
	
	public Chessboard(Context context) {
		super(context);
		constructor();
	}

	public Chessboard(Context context, AttributeSet attrs) {
		super(context, attrs);
		constructor();
		styleable(attrs);
	}
	
	// Convenience constructor
	private void constructor(){
		
		layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		chessboard = layoutInflater.inflate(com.skullab.chess.R.layout.chessboard_layout,null);
		
		boolean firstWhite = true ;
		for(int i=0 ; i<cellsId.length ; i++){
			// new line
			if(i % 8 == 0){
				firstWhite = firstWhite ? false : true ;  
			}
			//even
			if(i % 2 == 0){
				if(!firstWhite){
					blackCellsId.add(cellsId[i]);
				}else {
					blackCellsId.add(cellsId[i+1]);
				}
			// odd
			}else{
				if(!firstWhite){
					whiteCellsId.add(cellsId[i]);
				}else {
					whiteCellsId.add(cellsId[i-1]);
				}
			}
			
		}
		
		addView(chessboard);
	}
	// Set style on component (XML attributes)
	private void styleable(AttributeSet attrs){
		
		TypedArray t = getContext().obtainStyledAttributes(attrs,com.skullab.chess.R.styleable.Chessboard);
		
		boolean enable_notation = t.getBoolean(com.skullab.chess.R.styleable.Chessboard_enableNotation, true);
		int cells_size = (int)t.getDimension(com.skullab.chess.R.styleable.Chessboard_cellsSize,35);
		float text_size = t.getDimension(com.skullab.chess.R.styleable.Chessboard_notationTextSize,12);
		int text_color = t.getColor(com.skullab.chess.R.styleable.Chessboard_notationTextColor,Color.WHITE);
		String text_typeface = t.getString(com.skullab.chess.R.styleable.Chessboard_notationTypeface);
		
		if(text_typeface != null){
			AssetManager am = this.getResources().getAssets();
			Typeface type = Typeface.createFromAsset(am,text_typeface);
			setNotationTypeface(type);
		}
		/*@deprecated
		int white_cells_color = t.getColor(com.skullab.chess.R.styleable.Chessboard_whiteCellsColor,com.skullab.chess.R.color.white);
		int black_cells_color = t.getColor(com.skullab.chess.R.styleable.Chessboard_blackCellsColor,com.skullab.chess.R.color.black);
		setWhiteCellsColor(white_cells_color);
		setBlackCellsColor(black_cells_color);*/
		
		// white cells background
		Drawable w_c_b = t.getDrawable(com.skullab.chess.R.styleable.Chessboard_whiteCellsBackground);
		if(w_c_b != null){
			setWhiteCellsBackground(w_c_b);
		}else{
			int white_color = t.getColor(com.skullab.chess.R.styleable.Chessboard_whiteCellsBackground, Color.WHITE);
			setWhiteCellsBackground(white_color);
		}
		// black cells background
		Drawable b_c_b = t.getDrawable(com.skullab.chess.R.styleable.Chessboard_blackCellsBackground);
		if(b_c_b != null){
			setBlackCellsBackground(b_c_b);
		}else{
			int black_color = t.getColor(com.skullab.chess.R.styleable.Chessboard_blackCellsBackground, Color.BLACK);
			setBlackCellsBackground(black_color);
		}
		// notation background
		Drawable notation_drawable = t.getDrawable(com.skullab.chess.R.styleable.Chessboard_notationBackground);
		if(notation_drawable != null){
			setNotationBackground(notation_drawable);
		}else{
			int notation_color = t.getColor(com.skullab.chess.R.styleable.Chessboard_notationBackground,Color.BLACK);
			setNotationBackgroundColor(notation_color);
		}
		
		enableNotation(enable_notation);
		setCellsSize(cells_size);
		setNotationTextSize(text_size);
		setNotationTextColor(text_color);
	}
	/**
	 * Enables or not the display notation. By default the notation is always displayed.
	 * @param enable true to display otherwise false.
	 */
	public void enableNotation(boolean enable){
		for(int id : notationId){
			View v = chessboard.findViewById(id);
			v.setVisibility(enable ? View.VISIBLE : View.INVISIBLE);
		}
	}
	/**
	 * Sets the "white cells" color
	 * @param color
	 * @deprecated Use {@link setWhiteCellsBackground} instead
	 */
	public void setWhiteCellsColor(int color){
		for(int id : whiteCellsId){
			View v = chessboard.findViewById(id);
			v.setBackgroundColor(color);
		}
	}
	/**
	 * Sets the "black cells" color
	 * @param color
	 * @deprecated Use {@link setBlackCellsBackground} instead
	 */
	public void setBlackCellsColor(int color){
		for(int id : blackCellsId){
			View v = chessboard.findViewById(id);
			v.setBackgroundColor(color);
		}
	}
	/**
	 * Sets the "white cells" background color
	 * @param color
	 */
	public void setWhiteCellsBackground(int color){
		for(int id : whiteCellsId){
			View v = chessboard.findViewById(id);
			v.setBackgroundColor(color);
		}
	}
	/**
	 * Sets the "white cells" background drawable
	 * @param d drawable
	 */
	public void setWhiteCellsBackground(Drawable d){
		for(int id : whiteCellsId){
			View v = chessboard.findViewById(id);
			v.setBackgroundDrawable(d);
		}
	}
	/**
	 * Sets the "black cells" background color
	 * @param color
	 */
	public void setBlackCellsBackground(int color){
		for(int id : blackCellsId){
			View v = chessboard.findViewById(id);
			v.setBackgroundColor(color);
		}
	}
	/**
	 * Sets the "black cells" background drawable
	 * @param d drawable
	 */
	public void setBlackCellsBackground(Drawable d){
		for(int id : blackCellsId){
			View v = chessboard.findViewById(id);
			v.setBackgroundDrawable(d);
		}
	}
	/**
	 * Sets the background color of notation
	 * @param color
	 */
	public void setNotationBackgroundColor(int color){
		for(int id : notationId){
			View v = chessboard.findViewById(id);
			v.setBackgroundColor(color);
		}
	}
	/**
	 * Sets the background image of notation
	 * @param d
	 */
	public void setNotationBackground(Drawable d){
		for(int id : notationId){
			View v = chessboard.findViewById(id);
			v.setBackgroundDrawable(d);
		}
	}
	/**
	 * Sets the background image of notation
	 * @param resId
	 */
	public void setNotationBackground(int resId){
		for(int id : notationId){
			View v = chessboard.findViewById(id);
			v.setBackgroundResource(resId);
		}
	}
	/**
	 * Sets the text color of notation
	 * @param color
	 */
	public void setNotationTextColor(int color){
		for(int id : notationId){
			View v = chessboard.findViewById(id);
			if(id == com.skullab.chess.R.id.notation_up || id == com.skullab.chess.R.id.notation_down){
				for(int i = 0 ; i < 10 ; i++){
					TextView tv = (TextView)((TableRow)v).getChildAt(i);
					tv.setTextColor(color);
				}
			}else{
				((TextView)v).setTextColor(color);
			}
		}
	}
	/**
	 * Sets the text sixe of notation
	 * @param size
	 */
	public void setNotationTextSize(float size){
		for(int id : notationId){
			View v = chessboard.findViewById(id);
			if(id == com.skullab.chess.R.id.notation_up || id == com.skullab.chess.R.id.notation_down){
				for(int i = 0 ; i < 10 ; i++){
					TextView tv = (TextView)((TableRow)v).getChildAt(i);
					tv.setTextSize(size);
				}
			}else{
				((TextView)v).setTextSize(size);
			}
		}
	}
	/**
	 * Sets the text typeface of notation
	 * @param type
	 */
	public void setNotationTypeface(Typeface type){
		for(int id : notationId){
			View v = chessboard.findViewById(id);
			if(id == com.skullab.chess.R.id.notation_up || id == com.skullab.chess.R.id.notation_down){
				for(int i = 0 ; i < 10 ; i++){
					TextView tv = (TextView)((TableRow)v).getChildAt(i);
					tv.setTypeface(type);
				}
			}else{
				((TextView)v).setTypeface(type);
			}
		}
	}
	/**
	 * Gets the text size of notation
	 * @return the size
	 */
	public float getNotationTextSize(){
		TextView tv = (TextView)chessboard.findViewById(notationId[0]);
		return tv.getTextSize();
	}
	public ImageView getViewFromNotation(String notation){
		ImageView v = null ;
		for(int i=0 ; i<cellsName.length;i++){
			if(cellsName[i].equals(notation)){
				v = (ImageView)chessboard.findViewById(cellsId[i]);
				break;
			}
		}
		return v ;
	}
	/**
	 * This method allow to retrieve the image drawable of a cell by notation.
	 * The notation must be a string that represents the coordinate of the cell, for example "e5"
	 * @param notation The coordinate of the cell
	 * @return The view's image or null if no drawable has been assigned
	 */
	public Drawable getDrawableOnCell(String notation){
		ImageView v = getViewFromNotation(notation);
		return v.getDrawable() ;
	}
	/**
	 * Sets the drawable for a cell.
	 * @param d The drawable
	 * @param notation The coordinate of the cell
	 */
	public void setDrawableOnCell(Drawable d,String notation){
		ImageView v = getViewFromNotation(notation);
		v.setImageDrawable(d);
	}
	/**
	 * Allows to remove a drawable on a cell.
	 * @param notation The coordinate of the cell
	 */
	public void removeDrawableOnCell(String notation){
		ImageView v = getViewFromNotation(notation);
		v.setImageDrawable(null);
	}
	/**
	 * Sets the cell size.
	 * @param s size
	 */
	public void setCellsSize(int s){
		for(int id : cellsId){
			ImageView v = (ImageView)chessboard.findViewById(id);
			ViewGroup.LayoutParams lp = v.getLayoutParams();
			lp.width = s ;
			lp.height = s ;
			v.setLayoutParams(lp);
		}
	}
	/**
	 * Allows to set the {@link OnCellClickListener} for this Chessboard
	 * @param listener
	 */
	public void setOnCellClickListener(OnCellClickListener listener){
		mListener = listener ;
		for(int id : cellsId){
			View v = chessboard.findViewById(id);
			v.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String notation = getContext().getResources().getResourceEntryName(v.getId());
					if(mListener != null)mListener.onCellClick(notation);
				}
			});
		}
	}
	/**
	 * Interface definition for a callback to be invoked when a cell is clicked. 
	 * @author skullab.com
	 *
	 */
	public interface OnCellClickListener {
		/**
		 * Called when a cell has been clicked.
		 * @param notation The coordinate of the cell clicked
		 */
		public void onCellClick(String notation);
	}
}
