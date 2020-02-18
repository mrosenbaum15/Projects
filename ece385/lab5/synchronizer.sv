//These are synchronizers required for bringing asynchronous signals into the FPGA

//synchronizer with no reset (for switches/buttons)
//taken from given lab4 processor code
module sync (
	input  logic Clk, d, 
	output logic q
);

always_ff @ (posedge Clk)
begin
	q <= d;
end

endmodule