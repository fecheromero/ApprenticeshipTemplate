require 'rspec'
require_relative '../src/fibonacci'
describe 'test_subclasses_method' do

  context 'fibonacci.rb'  do
    specify 'subclasses method return all subclasses of a class' do
    class A

    end
    class B<A

    end
    class C<A

    end
    expect(A.subclasses.include?(B)).to eq(true)
    expect(A.subclasses.include?(C)).to eq(true)
      expect(A.subclasses.any?{|subclase| subclase!=C && subclase!=B}).to eq(false)
    end
end
  end