Displays a chessboard with (or without) notation around the board.

For a complete documentation for this component visit : http://skullab.site50.net/chessboard/javadoc/
<img src='http://img715.imageshack.us/img715/7537/device20111105024506.png' /> <img src='http://img821.imageshack.us/img821/9233/device20111106marble.png' />

Easy to use (to obtain the above result) :

```
<com.skullab.chess.Chessboard android:gravity="center" style="@style/ChessboardDefault"/>
```

```
<com.skullab.chess.Chessboard android:gravity="center" style="@style/ChessboardClassicMarble"/>   	
```

You can configure the board layout in XML with the following attributes:

**enableNotation
  * enable or not the notation around the board
  * required : boolean**

**whiteCellsBackground
  * allows to set the "white cells" color or drawable
  * required : reference or color**

**blackCellsBackground
  * allows to set the "black cells" color or drawable
  * required : reference or color**

**cellsSize
  * sets the cells size !
  * required : dimension**

**notationTextSize
  * sets the text size notation
  * required : dimension | float**

**notationTextColor
  * the color of the text
  * required : color**

**notationBackground
  * sets the background of the notation area
  * required : reference | color**

