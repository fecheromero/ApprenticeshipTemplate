require 'rspec'
require_relative '../src/tennis_game'
require_relative '../src/tennis_team'
require_relative '../src/../src/tennis_factory'
describe 'Tennis team1 vs team2 in a game' do
  let(:team2){TennisTeam.new('team2') }
  let(:team1){TennisTeam.new('team1')}
  let(:factory){TennisFactory.new}
  context 'normal score' do
    it 'win 1 services and have 15 points' do
      team1.winPointAgainst team2
      expect(team1.score.value).to eq(15)
    end

    it 'win 2 services have 30 poins' do
        team1.score=factory.points15
        team1.winPointAgainst team2
        expect(team1.score.value).to eq(30)
      end

    it 'win 3 services and have score in 40' do
      team1.score=factory.points30
      team1.winPointAgainst team2
      expect(team1.score.value).to eq(40)
    end
    it 'win 4 services and return to 0 points' do
      team1.score=factory.points40
      team1.winPointAgainst team2
      expect(team1.score.value).to eq(0)
      expect(team1.score.win?).to be_truthy
    end

  end

  context '40-40' do
    before do
    factory.makeA40_40 team1,team2
    end

    it'win and get advantage' do

      team1.winPointAgainst team2
      expect(team1.score.isAdvantage?).to be_truthy
  end
      it'win and get the game (score return 0)' do

        team1.winPointAgainst team2
        team1.winPointAgainst team2
        expect(team1.score.win?).to be_truthy
    end

       it'win and so the opponet lose your advantage' do

         team2.winPointAgainst team1

         team1.winPointAgainst team2

         expect(team2.score.value).to eq(40)
         expect(team2.score.isAdvantage?).to be_falsey

    end
  end
  end

describe 'Tennis team1 vs team2 in a set' do
  let(:team1){TennisTeam.new('team1')}
  let(:team2){TennisTeam.new('team2')}
  let(:game){TennisGame.new(team1,team2)}
  let(:factory){TennisFactory.new}
  context '' do
    it 'when a team win a game the set adds a point' do
      team1.score=factory.points40
      game.teamWinPoint team1
      expect(team1.score.value).to eq(0)
      expect(game.set.teams[team1]).to eq(1)

    end
    it 'make a team win a set 6-4' do
    factory.makeASetOfGame game,5,4
      team1.score=factory.points40
    expect{game.teamWinPoint team1}.to raise_exception('team1 games:6 team2 games:4 team1 Win.')

    end
    it 'a set 5-6 dont have winner (no exception)' do
      factory.makeASetOfGame game,5,5
      team1.score=factory.points40
      game.teamWinPoint team1
      expect(game.set.printStatusSet).to eq('team1 games:6 team2 games:5 ')
    end
  end


end