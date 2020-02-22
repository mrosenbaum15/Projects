// simple dff
// inputs: Clk (clock), Load, Reset, D
// ouput: Q
module d_flip_flop 
(
        input  Clk,
        input  Load,
        input  Reset,
        input  D,
        output logic Q
);

// rising edge or reset high
always_ff @ (posedge Clk or posedge Reset) begin
		
		  // next state back to zero
        if(Reset)
            Q <= 1'b0;
        else begin // load next state D if Load high, otherwise keep current state Q
				if(Load)	
					Q <= D;
				else
					Q <= Q;
		  end
end

endmodule
