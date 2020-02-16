module d_flip_flop
(
        input logic Clk,
        input logic Load,
        input logic Reset,
        input logic D,
        output logic Q
);

always_ff @ (posedge Clk or posedge Reset) begin


        if(Reset)
            Q <= 1'b0;
        else begin
				if(Load)	
					Q <= 1'b0;
				else
					Q <= Q;
		  end
end

endmodule
