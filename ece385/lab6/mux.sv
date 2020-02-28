// Inputs: ([15:0] in0, in1), select
// Outputs: [15:0] mux_output
// Description:
// Purpose:
module two_one_MUX
(
			input logic [15:0] in0,
			input logic [15:0] in1,
			input logic select,
			output logic [15:0] mux_output
);


always_comb

	begin

		// determing which data bit should be selected
		if(select == 1'b0)
			mux_output = in0;
		else
			mux_output = in1;

	end

endmodule

// Inputs: ([15:0] in0, in1, in2, in3), [1:0] select
// Outputs: [15:0] mux_output
// Description:
// Purpose:
module four_one_MUX
(
			input logic [15:0] in0,
			input logic [15:0] in1,
            input logic [15:0] in2,
            input logic [15:0] in3,
			input logic [1:0] select,
			output logic [15:0] mux_output
);


always_comb

	begin

		// determing which data bit should be selected
		if(select == 2'b00)
			mux_output = in0;
		else if(select == 2'b01)
			mux_output = in1;
        else if(select == 2'b10)
    		mux_output = in2;
        else
        	mux_output = in3;

	end

endmodule

// Inputs: ([2:0] in0, in1), [1:0] select
// Outputs: [2:0] mux_output
// Description:
// Purpose:
module two_one_MUX_3bit
(
			input logic [2:0] in0,
			input logic [2:0] in1,
			input logic [1:0] select,
			output logic [2:0] mux_output
);


always_comb

	begin

		// determing which data bit should be selected
		if(select == 2'b00)
			mux_output = in0;
		else
			mux_output = in1;

	end

endmodule

// Inputs: ([15:0] in0, in1, in2, in3), [3:0] select
// Outputs: [15:0] mux_output
// Description:
// Purpose:
module four_one_MUX_4bit_select
(
			input logic [15:0] in0,
			input logic [15:0] in1,
            input logic [15:0] in2,
            input logic [15:0] in3,
			input logic [3:0] select,
			output logic [15:0] mux_output
);


always_comb

	begin

		// determing which data bit should be selected
		// select will be either: 0001, 0010, 0100, 1000
		if(select == 4'h1)
			mux_output = in0;
		else if(select == 4'h2)
			mux_output = in1;
        else if(select == 4'h4)
    		mux_output = in2;
        else
        	mux_output = in3;

	end

endmodule
