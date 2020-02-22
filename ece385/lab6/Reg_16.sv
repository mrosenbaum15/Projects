// changed to be 16-bit register
module reg_16 (input  logic Clk, Reset, Load,
	      input  logic [15:0]  D, // 16-bit extension
	      output logic [15:0]  Data_Out); // 16-bit extension

    always_ff @ (posedge Clk)
    begin
	 	 if (Reset) //notice, this is a sycnrhonous reset, which is recommended on the FPGA
			  Data_Out <= 16'h0;
		 else if (Load)
			  Data_Out <= D;
    end

endmodule
