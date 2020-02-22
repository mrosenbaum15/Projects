// changed to be 8-bit register
module reg_8 (input  logic Clk, Reset, Load,
	      input  logic [7:0]  D, // 8-bit extension
	      output logic [7:0]  Data_Out); // 8-bit extension

    always_ff @ (posedge Clk)
    begin
	 	 if (Reset) //notice, this is a sycnrhonous reset, which is recommended on the FPGA
			  Data_Out <= 8'h0;
		 else if (Load)
			  Data_Out <= D;
    end

endmodule
