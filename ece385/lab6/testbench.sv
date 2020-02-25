module testbench();

timeunit 10ns;	// Half clock cycle at 50 MHz
			// This is the amount of time represented by #1 
timeprecision 1ns;

// These signals are internal because the processor will be 
// instantiated as a submodule in testbench.
logic Clk = 0;
logic Reset, Run, Continue;
logic CE;
logic UB;
logic LB;
logic OE;
logic WE;
logic [15:0] S;
logic	[11:0] LED;
logic [6:0] HEX0, HEX1, HEX2, HEX3, HEX4, HEX5, HEX6, HEX7; 
logic [19:0] ADDR;
wire [15:0] Data;

logic HIGH;
assign HIGH = 1'b1;

logic LOW;
assign LOW = 1'b0;
				
// A counter to count the instances where simulation results
// do no match with expected results
integer ErrorCnt = 0;
		
// Instantiating the DUT
// Make sure the module and signal names match with those in your design
lab6_toplevel lc3(.*);	

// Toggle the clock
// #1 means wait for a delay of 1 timeunit
always begin : CLOCK_GENERATION
#1 Clk = ~Clk;
end

initial begin: CLOCK_INITIALIZATION
    Clk = 0;
end 

// Testing begins here
// The initial block is not synthesizable
// Everything happens sequentially inside an initial block
// as in a software program
initial begin: TEST_VECTORS

Reset = HIGH;

#2 Reset = LOW;

#2 Reset = HIGH;

#2 Run = HIGH;

#2 Run = LOW;

#2 Run = HIGH;

#50 Continue = HIGH;

#50 Continue = LOW;

#50 Continue = HIGH;

#100 Continue = HIGH;

#100 Continue = LOW;

#100 Continue = HIGH;

end

endmodule